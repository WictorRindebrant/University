import os

path = os.getcwd()
file1 = "file_10000integers_A.txt"
file2 = "file_10000integers_B.txt"
# Namnen på dem två filerna som vi ska jobba med i detta uppdrag
# Funkar bara om dem ligger innanför cwd.


def finding_path(path, file1, file2):
    dirEntry = os.scandir(path)
    # Gör om stringen(adressen) till en scandir så att det går att
    # få ut namnen på filerna/mapparna som finns i stringen(adressen)
    # och för att få tillgång till en massa andra kommandon.

    for entry in dirEntry:
        # För varje fil/mapp som finns i dirEntry(adressen) så gör detta:
        if entry.name == file1:
            global file1_path
            file1_path = entry.path
        # om namnet på filen som man gå in i är samma som namnet på filen som
        # vi ska jobba med så sparas den sökvägen som en global variabel.
        # Vi sparar den som en global då det inte går att returna värden
        # ifrån en recursive function.

        if entry.name == file2:
            global file2_path
            file2_path = entry.path
        # om namnet på filen som man gå in i är samma som namnet på filen som
        # vi ska jobba med så sparas den sökvägen som en global variabel.
        # Vi sparar den som en global då det inte går att returna värden
        # ifrån en recursive function.

        if os.path.isdir(entry):
            # Om det är en mapp så gör följande:

            finding_path(entry, file1, file2)
            # Kör om funktionen med den aktuella mappens adress för att kolla
            # om den innehåller mappar/filer. Och med dem filnamnen som vi vill
            # veta sökvägen på.


def file_to_lst(adress):
    file = open(adress)
    # Öppnar filen som är bestämd av sökvägen till filen som man skickar in
    # till funktionen och sparar den till variabeln "file"

    full_text = ""
    for line in file:
        full_text += line
    # Sparar allt som finns i filen till en lång str variabel.

    file.close()
    # Stänger filen som vi öppnat högst upp i funktionen för att inte stöta
    # på några problem med att filen ligger öppen i senare kod.

    full_text = full_text.replace(",", "")
    full_text = full_text.replace(":", " ")
    full_text = full_text.replace("\n", " ")
    full_text = full_text.split(' ')
    # Tar bort alla konstiga teckan som finns i filen och splittar upp
    # orden(talen) om det är mellanrum mellan dem.

    int_lst = []
    counter = 0
    for i in full_text:
        if counter < 10000:
            int_lst.append(int(i))
            counter += 1
    # Skapar en lista för alla 10000 orden som finns i filen så att det
    # blir lättare att arbeta med.

    return int_lst


def count_different(lst):
    int_set = set()
    for i in lst:
        int_set.add(i)
    # Lägget till alla tal i int listan som man skickar in i funktionen
    # till en set lista. Gör man det så får man inga duplicates.

    return len(int_set)


def count_occurrences(lst):
    dict_lst = {}
    for i in lst:
        dict_lst[i] = lst.count(i)
    # För varje tal i listan med tal, som man skickar in i funktionen
    # så lägger den till talet som key i en dictionary och key value
    # kommer att bli så många gånger som talet förekommer i listan.

    return dict_lst


def top_five(dict_lst):
    key_sorted = sorted(dict_lst.items(), key=lambda lst: lst[1], reverse=True)
    # gör en lista av tuples med dem två paren i en dictionary (key och value)
    # som man sedan sorterar efter det andra värdet av alla listorna som är
    # samma som key valuet av en dictionary.

    key_five = [key_sorted[0], key_sorted[1], key_sorted[2], key_sorted[3],
                key_sorted[4]]
    # sparar dem 5 mest förekommande talen ifrån listan som man skickar in i
    # funktionen till en lista med tuples där det står vilket tal det är och
    # hur många gånger talet förekommer.

    key_five = dict(key_five)
    # gör sedan om den listan till en dictionary igen men som nu är sorterad
    # och som bara innehåller dem 5 mest förekommande talen och hur ofta dem
    # förekommer.

    return key_five


finding_path(path, file1, file2)
# Hittar filsökvägen på namnen på filerna som vi skrev in i början.
# Dem kommer bli sparade till globala variablar då vi inte kan
# returna värden ifrån en recusive function.

int_lst1 = file_to_lst(file1_path)
# Om man skriver in en fils sökväg som innehåller en massa tal
# (skiljda med mellanrum) så ska funktionen göra det till en
# lista med talen (för fil nm.1).

int_lst2 = file_to_lst(file2_path)
# Om man skriver in en fils sökväg som innehåller en massa tal
# (skiljda med mellanrum) så ska funktionen göra det till en
# lista med talen (för fil nm.2)

set_lst1 = count_different(int_lst1)
# Skickar in en lista med integers som görs om till en set lista
# så att man kan få ut hur många olika tal det finns i listan.
# som sedan sparas som i variablen "set_lst1"

set_lst2 = count_different(int_lst2)
# Skickar in en lista med integers som görs om till en set lista
# så att man kan få ut hur många olika tal det finns i listan.
# som sedan sparas som i variablen "set_lst2"

dict_lst1 = count_occurrences(int_lst1)
# skickar in en lista med integers som kommer att göras om
# till en dictionary med alla olika talen och hur många gånger
# alla talen förekommer. Och sedan spara det i variabeln "dict_lst1"

key_five1 = top_five(dict_lst1)
# Skickar in en dictionary lista i funktionen top_five som kommer
# göra om dictionaryn till en lista och sortera den efter key value
# av dictionaryn. När den sedan är sorterad så görs den om till
# dictionary igen men som nu är sorterad. Sedan sparar man bara
# dem 5 mest förekommande värderna i en ny variabel och skickar
# tillbaka den som sedan sparas i variabeln "key_five1"

key_value1 = key_five1.values()
key_value1 = list(key_value1)
key_five1 = list(key_five1)
# Separerar värderna av en dictionary till två olika listor,
# en lista med key och den andra med value, så att man sedan
# kan göra en snygg print av alla värderna.

dict_lst2 = count_occurrences(int_lst2)
# skickar in en lista med integers som kommer att göras om
# till en dictionary med alla olika talen och hur många gånger
# alla talen förekommer. Och sedan spara det i variabeln "dict_lst2"

key_five2 = top_five(dict_lst2)
# Skickar in en dictionary lista i funktionen top_five som kommer
# göra om dictionaryn till en lista och sortera den efter key value
# av dictionaryn. När den sedan är sorterad så görs den om till
# dictionary igen men som nu är sorterad. Sedan sparar man bara
# dem 5 mest förekommande värderna i en ny variabel och skickar
# tillbaka den som sedan sparas i variabeln "key_five2"

key_value2 = key_five2.values()
key_value2 = list(key_value2)
key_five2 = list(key_five2)
# Separerar värderna av en dictionary till två olika listor,
# en lista med key och den andra med value, så att man sedan
# kan göra en snygg print av alla värderna.

print("\nAll the different numbers from fileA:")
print(set_lst1)
print("\nAll the different numbers from fileB:")
print(set_lst2)

print("\nThe top 5 most common numbers in fileA is number:", key_five1[0],
      "counted", key_value1[0], "times. number:", key_five1[1], "counted",
      key_value1[1], "times. number:", key_five1[2], "counted", key_value1[2],
      "times. number:", key_five1[3], "counted", key_value1[3],
      "times. number:", key_five1[4], "counted", key_value1[4], "times.")

print("The top 5 most common numbers in fileB is number:", key_five2[0],
      "counted", key_value2[0], "times. number:", key_five2[1], "counted",
      key_value2[1], "times. number:", key_five2[2], "counted", key_value2[2],
      "times. number:", key_five2[3], "counted", key_value2[3],
      "times. number:", key_five2[4], "counted", key_value2[4], "times.\n")
