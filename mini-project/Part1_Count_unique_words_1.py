import os

base_path = os.getcwd() + "\\"
file1 = "output_11215_words.txt"
holy_path = base_path + file1
# Skapar en path för output av filen "holy_grail.txt"
# Som fungerar på allas datorer om man har samma mapp.

file2 = "output_1918499_words.txt"
news_path = base_path + file2
# Skapar en path för output av filen "eng_news_100K-sentences.txt"
# Som fungerar på allas datorer om man har samma mapp.


def file_to_lst(path):
    words = []
    with open(path, "r", encoding="utf-8") as file:
        for word in file:
            words.append(word.strip())

    return words
    # Funtionen file_to_lst läser av filens sökväg som man skickar
    # in i funktionen och gör en lista av allt som finns i
    # textfilen där allt i listan är separerat med ett mellanrun.


def uniqe_words(words):
    return len(set(words))
    # Funktionen unique_words gör om en lista med ord till ett set
    # fast utan några duplicates.


def occurrences_words(words):
    occurrences = {}
    for word in words:
        if len(word) > 4:
            if word not in occurrences:
                occurrences[word] = 0
            occurrences[word] += 1

    return occurrences
    # Funtionen occurrences_words gör om en lista med ord till en
    # dictionary som inte har några duplicates men som visar hur
    # många gånger orden förekommer i listan som har duplicates.


holy_words = file_to_lst(holy_path)
news_words = file_to_lst(news_path)
# Skickar in dem två olika filsökvägarna för dem två olika
# filerna till funtionen file_to_lst som gör om dem till
# en lista med ord som filerna innehåller. Som sedan returnas
# och sparas i två olika variablar.

holy_uniq = uniqe_words(holy_words)
news_uniq = uniqe_words(news_words)
# Skivar in två olika listor med ord in i funktionen unique_words
# som gör om listorna till sets (som då inte innehåller duplicates)
# som sedan returnas och sparas som två olika variablar.

holy_occ = occurrences_words(holy_words)
news_occ = occurrences_words(news_words)
# Skickar in två olika listor med ord in i funktionen occurrences_words
# som gör om listan till en dictionary där orden sparas som key
# (utan duplicates) och value för alla orden för hur många gånger
# orden återkommer i listan som man skickade in i funktionen.
# dictionaryn returnas sen och sparas som två olika variablar.

holy_items = list(holy_occ.items())
holy_sorted = sorted(holy_items, key=lambda tuples: tuples[1], reverse=True)

news_items = list(news_occ.items())
news_sorted = sorted(news_items, key=lambda tuples: tuples[1], reverse=True)
# Man gör om dictionaryn till en lista med tuples som innehåller key och value
# för varje ord man hade med i dictionaryn. Man gör detta då man inte kan
# sortera en dictionary. Sedan så sorterar man listan med tuples beroende på
# andra värdet av tuplesen ([1] som är value i en dictionary och i detta fall
# värdet på hur många gånger ordet förekommer.)

print(f"\nTotal amount of unique words in {file1} are: {holy_uniq}")
for i in range(10):
    print(f"{holy_sorted[i][0]:9} | {holy_sorted[i][1]}")

print(f"\nTotal amount of unique words in {file2} are: {news_uniq}")
for i in range(10):
    print(f"{news_sorted[i][0]:9} | {news_sorted[i][1]}")
# Printar ut informationen som är lagrad i file_name och dif_words
# Sedan så printar den ut key och value ifrån en sorterad lista (efter value)
# som innehåller en massa tuples som har två värden (key och value).
# :9 står för att det ska vara 9 plattser innan | kommer.
