from dataclasses import dataclass
# importerar dataclass från biblioteket dataclasses


@dataclass
class MultiDisplay():
    # Gör en class som heter "MultiDisplay"
    message = ""
    count = 0
    # I classen så har jag två variablar, en string och en integer

    def set_message(self, message):
        self.message = message
        # ändrar "MultiDisplay" classens variabel "message" till message
        # som man skickar in till en här funktionen.

    def set_count(self, count):
        self.count = count
        # ändrar "MultiDisplay" classens variabel "count" till count
        # som man skickar in till en här funktionen.

    def to_string(self):
        return "Message: " + self.message + ", Count: " + str(self.count)
        # Skickar tillbaka värderna på variablarna "message" och "count"
        # som finns i classen "MultiDisplay" med två strängar som visar
        # tydligt vad det är man skickar tillbaka.

    def display(self):
        for i in range(0, self.count):
            print(self.message)
        # Printar ut variablen "message" som är lagrad i en variabel som
        # finns i classen "MultiDisplay" lika många gånger som värdet är på
        # "count" som också är lagrad i en variabel som finns i classen
        # "MultiDisplay"

    def set_display(self, message, count):
        self.message = message
        self.count = count
        self.display()
        # ändrar "MultiDisplay" classens variabel "message" och "count"
        # till dem två värderna som man skickar in i den här funktionen.
        # Sen så kallar man på funktionen "display"

    def get_message(self):
        return self.message
        # Skickar tillbaka värdet på classen "MultiDisplay" variaabel "message"
