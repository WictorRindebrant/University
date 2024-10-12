import machine
import time
import ubinascii
import network
import micropython
from mqtt import MQTTClient
from dht import DHT11

# WiFi information.
# WIFI_SSID = "ASUS_A8_2G"
# WIFI_PASS = "Barnkalas10"
WIFI_SSID = "Rindebrant"
WIFI_PASS = "barnkalas10"

# Pins used on the Pico.
led1 = machine.Pin("GP0", machine.Pin.OUT)
led2 = machine.Pin("GP1", machine.Pin.OUT)
led3 = machine.Pin("GP2", machine.Pin.OUT)
led4 = machine.Pin("GP3", machine.Pin.OUT)
led5 = machine.Pin("GP4", machine.Pin.OUT)
led6 = machine.Pin("GP5", machine.Pin.OUT)
led7 = machine.Pin("GP6", machine.Pin.OUT)
tempSensor = DHT11(machine.Pin(28))

# How often information is updated to the website.
publish_time_interval = 60000    # ms
last_sent_ticks = 0  # ms

# Status of the light (start as ON).
light_status = "ON"

# Website infromation.
AIO_SERVER = "io.adafruit.com"
AIO_PORT = 1883
AIO_USER = "wr222bp"
AIO_KEY = "aio_Zkti82CnqDAJhNHHlqh2YZ0sq6jG"
AIO_CLIENT_ID = ubinascii.hexlify(machine.unique_id())
AIO_LIGHTS_FEED = "wr222bp/feeds/lights"
AIO_TEMP_FEED = "wr222bp/feeds/temp"

# Funtion that tries to connect the Pico to the WiFi.
def connect():
    wlan = network.WLAN(network.STA_IF)

    if not wlan.isconnected():               
        print('Connecting to ', WIFI_SSID)
        wlan.active(True)                    
        
        wlan.config(pm = 0xa11140)
        wlan.connect(WIFI_SSID, WIFI_PASS) 
        print('Establishing connection...', end='')
       
        while not wlan.isconnected() and wlan.status() >= 0:
            print('.', end='')
            time.sleep(1)
   
    ip = wlan.ifconfig()[0]
    print('\nConnected from {}'.format(ip))
    return ip 

# Handles the functionality when pressing the light button on the website.
def light_button_cb(topic, msg):
    global light_status         
    if msg == b"ON":             
        ledOn()
        light_status = "ON"      
    elif msg == b"OFF":          
        allLedOff()
        light_status = "OFF"         
    else:                        
        print("Unknown message")
    print("Light Status:", light_status)

# Sends the infromation about the temperature to the website every x ms.
def send_temp():
    global publish_time_interval
    global last_sent_ticks
    last_publish = time.ticks_ms()

    if ((last_publish - last_sent_ticks) < publish_time_interval):
        return; 

    temp = get_temp()
    print("Publishing: {0} Celsius to {1}      ".format(temp, AIO_TEMP_FEED), end='')
    try:
        client.publish(topic=AIO_TEMP_FEED, msg=str(temp))
        print("[DONE]")
    except Exception as e:
        print("[FAILED]")
    finally:
        last_sent_ticks = time.ticks_ms()

# Gets the temperature and returns it.
def get_temp():
    tempSensor.measure()
    return tempSensor.temperature()

# Turns all the led lights off.
def allLedOff():
    led1.off()
    led2.off()
    led3.off()
    led4.off()
    led5.off()
    led6.off()
    led7.off()

# Turns one of the led light on depending on temperature.
def ledOn():
    temp = get_temp()

    if (temp <= 23):
        allLedOff()
        led1.on()
    elif (temp <= 24):
        allLedOff()
        led2.on()
    elif (temp <= 25):
        allLedOff()
        led3.on()
    elif (temp <= 26):
        allLedOff()
        led4.on()
    elif (temp <= 27):
        allLedOff()
        led5.on()
    elif (temp <= 28):
        allLedOff()
        led6.on()
    else:
        allLedOff()
        led7.on()


# Tries to connect to the WiFi.
try:
    ip = connect()
except KeyboardInterrupt:
    print("Keyboard interrupt")

# Use the MQTT protocol to connect to the website.
client = MQTTClient(AIO_CLIENT_ID, AIO_SERVER, AIO_PORT, AIO_USER, AIO_KEY)

# Subscribed messages will be delivered to this callback
client.set_callback(light_button_cb)
client.connect()
client.subscribe(AIO_LIGHTS_FEED)
print("Connected to %s, subscribed to %s topic" % (AIO_SERVER, AIO_LIGHTS_FEED))

# Infinity while loop that updates the status of the temperature and light.
try:                               
    while True:
        client.check_msg()
        send_temp()
        if light_status == "ON":
            ledOn()
        elif light_status == "OFF":
            allLedOff()
# If it gets to this it will disconnect from the website.
finally:                  
    client.disconnect()  
    client = None
    print("Disconnected from the website.")

main()
