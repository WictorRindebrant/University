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


def filepath_to_list(file):
    file = open(file)
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


def mean(lst):
    sum = 0
    counter = 0
    for c in lst:
        sum += int(c)
        counter += 1
    ave = sum / counter
    return ave
    # Skickar in en lista med integers som den beräknar
    # average talet på och sedan returnar det talet.


def std(lst):
    ave = mean(lst)
    # Kollar på funtionen "mean" med listan som vi skickat in i funktionen
    # "std" och sparar return värdet ifrån "mean funtionen" till "ave"

    square_lst = []
    square = 0
    counter = 0

    for c in lst:
        square_lst.append((c - ave) ** 2)
        counter += 1
    for i in square_lst:
        square += i
    variance = (1 / counter) * square
    square_variance = variance ** (1/2)
    # Två for loopar och två beräkningar för att räkna ut standard deviationen
    # på listan.

    return square_variance


finding_path(path, file1, file2)
# Hittar filsökvägen på namnen på filerna som vi skrev in i början.
# Dem kommer bli sparade till globala variablar då vi inte kan
# returna värden ifrån en recusive function.

int_lst1 = filepath_to_list(file1_path)
# Om man skriver in en fils sökväg som innehåller en massa tal
# (skiljda med mellanrum) så ska funktionen göra det till en
# lista med talen (för fil nm.1).

int_lst2 = filepath_to_list(file2_path)
# Om man skriver in en fils sökväg som innehåller en massa tal
# (skiljda med mellanrum) så ska funktionen göra det till en
# lista med talen (för fil nm.2)

print("")
print("The mean of the integers in: '" + file1 + "' is", mean(int_lst1))
print("The mean of the integers in: '" + file2 + "' is", mean(int_lst2))
print("")
print("The standard deviation of the integers in: '" + file1 + "' is",
      std(int_lst1))
print("The standard deviation of the integers in: '" + file2 + "' is",
      std(int_lst2))
print("")
