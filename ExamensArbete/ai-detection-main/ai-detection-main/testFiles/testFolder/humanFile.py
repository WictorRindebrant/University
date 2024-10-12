import login
import send
import readfile
import os

os.chdir(os.getcwd() + "/detection-code")
# print("NEW PATH: " + os.getcwd())

def main():
    # Authentication to copyleaks:
    key = "dac39799-6fd6-48d1-89aa-d576f05f7c66"
    email = "wrindebrant@gmail.com"
    access_token = login.get_access_token(key, email)

    # File handling:
    data = readfile.get_data()

    # Send to copyleaks:
    response = send.send_data(str(access_token['access_token']), data["filetext"], data["filemame"])
    print(response)

main()