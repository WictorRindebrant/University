import os

path = os.getcwd()
print(path)
# Skriver ut path som är satt till cwd som står för: Current Working Directory


def print_subdirectories(dir_path):
    dirEntry = os.scandir(dir_path)
    # Gör om stringen(adressen) till en scandir så att det
    # går att få ut namnen på filerna/mapparna som finns i
    # stringen(adressen) och för att få tillgång till en massa andra kommandon.

    for entry in dirEntry:
        # För varje fil/mapp som finns i dirEntry(adressen) så gör detta:

        if os.path.isdir(entry):
            # Om det är en mapp så gör följande:

            diff = len((entry.path.split('\\'))) - len(path.split('\\'))
            # Beräknar skillnaden på hur många mappar det är i cwd
            # och hur många mappar det är i den aktuella mappen som
            # man kör med for loopen, så att man i printen vet
            # hur många tabs som man ska hoppa ut.

            print("\t" * diff + entry.name)
            # printar ut den aktuella mappen med x antal gånger tabs, och med
            # hela adressen.

            print_subdirectories(entry)
            # Kör om funktionen med den aktuella mappens adress för att
            # kolla om den innehåller mappar.


print_subdirectories(path)
