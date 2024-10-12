import os
import HashSet as hset
import BstMap as bst

base_path = os.getcwd() + "\\"
file1 = "output_11215_words.txt"
holy_path = base_path + file1
# Skapar en path för filen "output_11215_words.txt"
# Som fungerar på allas datorer om man har samma mapp.

file2 = "output_1918499_words.txt"
news_path = base_path + file2
# Skapar en path för filen "output_1918499_words.txt"
# Som fungerar på allas datorer om man har samma mapp.


def file_to_lst(path):
    words = []
    with open(path, "r", encoding="utf-8") as file:
        for word in file:
            words.append(word.strip())

    return words
    # Funtionen file_to_lst läser av filens sökväg som man skickar
    # in i funktionen och gör en lista av alla ord som finns i
    # filen om det är 1 ord per rad i filen.


def unique_words(words):
    unique = hset.HashSet()
    # Sparar hset.Hashset() i en varaibel så att man inte behöver
    # skriva allt det varje gång man vill komma in i filen HashSet
    # och i klassen HashSet.

    unique.init()
    # Kallar på funktionen init() ifrån filen HashSet i klassen HashSet.
    # Funktionen skapar en bucket som har 8st listor i sig.

    for word in words:
        unique.add(word)
    # För varje ord som finns lagrat i variabeln words (som man skickar in
    # till funktionen) så skickar den in varje ord till funktionen add()
    # i filen HashSet i classen HashSet.

    return unique.get_size()
    # Returnar det som returnas ifrån funktionen get_size() ifrån filen
    # HashSet ifrån klassen HashSet. Som är ett värde av alla element
    # som finns i bucketen i filen HashSet.


def occurrences_words(words):
    occurrences = bst.BstMap()
    # Sparar bst.BstMap() i en varaibel så att man inte behöver
    # skriva allt det varje gång man vill komma in i filen HashSet
    # och i klassen HashSet.

    for word in words:
        if len(word) > 4:
            if occurrences.get(word) is None:
                occurrences.put(word, 0)
            value = occurrences.get(word) + 1
            occurrences.put(word, value)
    # För varje ord som finns i varaibeln words (som man skickar in
    # till funktionen) så kollar den först om ordet är större än 4
    # om ordet är större än 4 så kollar den om ordet redan finns med
    # i BstTrädet med hjälp av funktionen get(). Om det inte finns
    # med så lägger den in ordet i trädet på rätt possition med hjälp
    # av funktionen put. Med en key som är ordet och value som börjar
    # på 0 men som direkt efter blir till 1. Det värdet ändras varje
    # gång som ordet redan finns med i trädet till 1 mer än tidigare.

    return occurrences.as_list()
    # Returnar det som returnas ifrån funktionen as_list() ifrån filen
    # BstMap ifrån klassen BstMap.


def bucket_size(words1, words2):
    unique = hset.HashSet()
    # Sparar hset.Hashset() i en varaibel så att man inte behöver
    # skriva allt det varje gång man vill komma in i filen HashSet
    # och i klassen HashSet.

    unique.init()
    # Kallar på funktionen init() ifrån filen HashSet i klassen HashSet.
    # Funktionen skapar en bucket som har 8st listor i sig.

    for word in words1:
        unique.add(word)

    for word in words2:
        unique.add(word)
    # För varje ord som finns lagrat i dem två olika varaiblarna som man
    # skickar in till funktionen så skickar den in alla orden till filen
    # HashSet till klassen HashSet till funktionen add().

    return unique.max_bucket_size()
    # Returnar det som returnas ifrån funktionen max_bucket_size() ifrån filen
    # HashSet ifrån klassen HashSet. Som är ett värde av längden av den största
    # listan som finns i bucketen.


def max_depth(words1, words2):
    occurrences = bst.BstMap()
    # Sparar bst.BstMap() i en varaibel så att man inte behöver
    # skriva allt det varje gång man vill komma in i filen HashSet
    # och i klassen HashSet.

    for word in words1:
        if occurrences.get(word) is None:
            occurrences.put(word, 0)
        value = occurrences.get(word) + 1
        occurrences.put(word, value)

    for word in words2:
        if occurrences.get(word) is None:
            occurrences.put(word, 0)
        value = occurrences.get(word) + 1
        occurrences.put(word, value)
        # För varje ord som finns i varaiblarna (som man skickar in
        # till funktionen) så kollar den om ordet redan finns med
        # i BstTrädet med hjälp av funktionen get(). Om det inte finns
        # med så lägger den in ordet i trädet på rätt possition med hjälp
        # av funktionen put. Med en key som är ordet och value som börjar
        # på 0 men som direkt efter blir till 1. Det värdet ändras varje
        # gång som ordet redan finns med i trädet till 1 mer än tidigare.

    return occurrences.max_depth()
    # Returnar det som returnas ifrån funktionen max_depth() ifrån filen
    # BstMap ifrån klassen BstMap.


holy_words = file_to_lst(holy_path)
news_words = file_to_lst(news_path)
# Skickar in dem två olika filsökvägarna för dem två olika
# filerna till funtionen file_to_lst som gör om dem till
# en lista med ord som filerna innehåller. Som sedan returnas
# och sparas i två olika variablar.

holy_unique = unique_words(holy_words)
news_unique = unique_words(news_words)
# Skickar in två olika listor med ord in i funktionen unique_words
# som sedan returnar lägden av en lista med alla dem unika orden.
# som sparas i två olika variablar.

holy_occurr = occurrences_words(holy_words)
news_occurr = occurrences_words(news_words)
# Skickar in två olika listor med ord in i funktionen occurrences_words
# som returnar en lista med listor i sig som har två värden (Ett key och ett
# value) key är ordet och value är hur ofta ordet förekommer i listan som
# man skickade in till funktionen. Man sparar sedan sem listorna i två
# olika variablar för dem två olika filerna.

holy_sorted = sorted(holy_occurr, key=lambda tuples: tuples[1], reverse=True)
news_sorted = sorted(news_occurr, key=lambda tuples: tuples[1], reverse=True)
# Här så sorterar man listan med listor beroende på andra värdet av listan
# ([1] som i detta fall värdet på hur många gånger ordet förekommer.)

print(f"\nTotal amount of unique words in {file1} are: {holy_unique}")
for i in range(10):
    print(f"{holy_sorted[i][0]:9} | {holy_sorted[i][1]}")

print(f"\nTotal amount of unique words in {file2} are: {news_unique}")
for i in range(10):
    print(f"{news_sorted[i][0]:9} | {news_sorted[i][1]}")
    # Printar ut dem 10 mest förekommande orden ifrån 2 olika filer
    # med hjälp av att vi sorterat listorna med listor med andra
    # värdet som i detta fall var value som är hur ofta orden
    # förekommer i filen som man skickade in till koden.

print(f"\nThe max bucket list size for {file1} and {file2} is", end=" ")
print(bucket_size(holy_words, news_words))
# Printar ut det funktionen bucket_size returnar när man skickat in
# ord ifrån två olika filer in till koden HashSet.

print(f"The max depth size for {file1} and {file2} is", end=" ")
print(max_depth(holy_words, news_words))
# Printar ut det funktionen max_depth returnar när man skickat in
# ord ifrån två olika filer in till koden BstMap.
