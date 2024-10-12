import os

path = os.getcwd()
file1 = "holy_grail.txt"
file2 = "eng_news_100K-sentences.txt"
file1_save = "compared_words1.txt"
file2_save = "compared_words2.txt"
# Namnen på dem två filerna som vi ska jobba med i detta uppdrag
# Funkar bara om dem ligger innanför cwd.


def finding_path(path, file1, file2, file1_save, file2_save):
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

        if entry.name == file1_save:
            global file1_save_path
            file1_save_path = entry.path
        # om namnet på filen som man gå in i är samma som namnet på filen som
        # vi ska jobba med så sparas den sökvägen som en global variabel.
        # Vi sparar den som en global då det inte går att returna värden
        # ifrån en recursive function.

        if entry.name == file2_save:
            global file2_save_path
            file2_save_path = entry.path
        # om namnet på filen som man gå in i är samma som namnet på filen som
        # vi ska jobba med så sparas den sökvägen som en global variabel.
        # Vi sparar den som en global då det inte går att returna värden
        # ifrån en recursive function.

        if os.path.isdir(entry):
            # Om det är en mapp så gör följande:

            finding_path(entry, file1, file2, file1_save, file2_save)
            # Kör om funktionen med den aktuella mappens adress för att kolla
            # om den innehåller mappar/filer. Och med dem filnamnen som vi vill
            # veta sökvägen på.


def read_file(file_path):
    file = open(file_path, encoding='utf-8')
    # Öppnar filen som är bestämd av sökvägen till filen som man skickar in
    # till funktionen och sparar den till variabeln "file".
    # encoding='utf-8' behövs då det strular att öppna filen med windows.

    words_lst = []
    for line in file:
        for word in line.split():
            words_lst.append(word)
    # för varje "ord" för varje rad i filen så lägger den till orden
    # till en ny lista.

    file.close()
    # Stänger filen som vi öppnat högst upp i funktionen för att inte stöta
    # på några problem med att filen ligger öppen i senare kod.

    return words_lst


def getwords(words_lst):
    new_lst = []

    for word in words_lst:
        word = word.lower()
        # Gör om alla bokstäver i alla "ord" till bara små bokstäver

        new_word = ""
        for char in word:
            if char.isascii():
                if char.isalpha() or char == "'" or char == "-":
                    new_word += char
                else:
                    break
        # for-loopen kollar alla bokstäver/symboler i alla ord. Om dem
        # är en bokstav eller symbolerna ' och - så bils det ett nytt
        # ord som bara innehåler dem symbolerna + alla bokstäver.

        if new_word != "" and new_word != "-" * len(new_word):
            if new_word != "'" * len(new_word):
                if len(new_word) > 1 or new_word == "i" or new_word == "a":
                    # Om det nya ordet som vi skapat inte bara består av
                    # symbolerna "-" eller "'" eller är helt tomma och om
                    # ordet är längre än 1 bokstav ( som inte är "i" eller "a")
                    # så kör while looparna nedan.

                    while new_word[0] == "'" or new_word[0] == "-":
                        new_word = new_word.replace(new_word[0], '')
                    while new_word[-1] == "'" or new_word[-1] == "-":
                        new_word = new_word.replace(new_word[-1], '')
                    # While looparna kollar om det nya ordet börjar eller
                    # slutar med symbolerna "'" eller "-" och isf tar den bort
                    # dem sybolerna ifrån ordet.

                    if len(new_word) > 1 or new_word == "i" or new_word == "a":
                        # Sista if statementet kollar igen om ordet är längre
                        # än 1 bokstav som inte är bokstaven/ordet
                        # ( "i" and "a")
                        new_lst.append(new_word)
                        # Om ordet kommer igenom all if statements ovan så
                        # läggs ordet till i listan "new_lst"
    return new_lst


def save_words(file_path, words):
    file = open(file_path, "w")
    # Öppnar filen som är bestämd av sökvägen till filen som man skickar in
    # till funktionen och sparar den till variabeln "file".

    for c in words:
        file.write(c + " ")
    # för varje ord som vi har i listan som vi skickar in i funktionen så
    # skriver den in det i filen som vi öppnat separerat med mellanrum.

    file.close()
    # Stänger filen som vi öppnat högst upp i funktionen för att inte stöta
    # på några problem med att filen ligger öppen i senare kod.


finding_path(path, file1, file2, file1_save, file2_save)
# Hittar filsökvägen på namnen på filerna som vi skrev in i början.
# Dem kommer bli sparade till globala variablar då vi inte kan
# returna värden ifrån en recusive function.

words_lst1 = read_file(file1_path)
# Läser av in filsökvägen som vi skickar in i funktionen, öppnar filen,
# skriver över allt som finns i filen till en lista som vi sedan
# sparar i en variabel som heter "words_lst1" (för file1)

words_lst2 = read_file(file2_path)
# Läser av in filsökvägen som vi skickar in i funktionen, öppnar filen,
# skriver över allt som finns i filen till en lista som vi sedan
# sparar i en variabel som heter "words_lst2" (för file2)

get_words1 = getwords(words_lst1)
# Skickar in listan som vi skapade i funktionen "read_file" till funktionen
# "getwords" som kommer att filtrera ut alla konstiga tecken och siffror
# i listan och skapa en ny lista som bara inehåller vanliga ord.
# som sparas i variabeln "get_words1" (för words_lst1 / för file1)

get_words2 = getwords(words_lst2)
# Skickar in listan som vi skapade i funktionen "read_file" till funktionen
# "getwords" som kommer att filtrera ut alla konstiga tecken och siffror
# i listan och skapa en ny lista som bara inehåller vanliga ord.
# som sparas i variabeln "get_words2" (för words_lst2 / för file2)

save_words(file1_save_path, get_words1)
# Skickar in den nya listan som bara innehåller ord till funktionen save_words
# som kommer att spara allt som står i den nya listan till en text fil
# som i detta fall är "file1_save_path" som vi fick fram sökvägen till i
# funktionen finding_path.

save_words(file2_save_path, get_words2)
# Skickar in den nya listan som bara innehåller ord till funktionen save_words
# som kommer att spara allt som står i den nya listan till en text fil
# som i detta fall är "file2_save_path" som vi fick fram sökvägen till i
# funktionen finding_path.

print("Längden av listan för fileA: ", len(get_words1))
print("Längden av listan för fileB: ", len(get_words2))
# Dem här prinstsen visar bara hur många ord det finns i dem två olika listorna
