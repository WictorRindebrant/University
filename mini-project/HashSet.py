from dataclasses import dataclass
from typing import List


@dataclass
class HashSet:
    buckets: List[List] = None
    size: int = 0

    def init(self):
        self.size = 0
        self.buckets = [[] for i in range(8)]

    # Computes hash value for a word (a string)
    def get_hash(self, word):
        var = 0
        for i in word:
            var += ord(i)
        # I for loopen så lägger den till värdet för varje bokstav i ordet
        # som man skickar in i funktionen till variabeln var.
        # Det värdet är baserat på bokstavens asccii värde
        # som man får ut av funktionen "ord()"

        hash_value = var % len(self.buckets)
        # Här ger man ordet en platts i listan beroende på hur stor listan
        # är och beroende på vilket värde som ordet fått ifrån for loopen ovan.

        return hash_value

    # Doubles size of bucket list
    def rehash(self):
        self.size = 0
        old = self.buckets
        # Sparar alla värden som finns i bucketen till varaibeln old.

        self.buckets = [[] for i in range(len(self.buckets * 2))]
        # Skriver över self bucket med en ny lista som har dubbelt så många
        # listor i sig som den förra bucketen.

        for lst in old:
            for word in lst:
                HashSet.add(self, word)
        # För varje ord i den gamla bucketen så kör dem orden med funktionen
        # Add() så att dem läggs till i den nya listan

    # Adds a word to set if not already added
    def add(self, word):
        hash_value = self.get_hash(word)
        # Beräknar ordets (som man skickar in i funktionen) position i bucketen
        # med hjälp av funktionen "get_hash" och sparar det
        # värdet i variabeln has_value

        if word not in self.buckets[hash_value]:
            self.buckets[hash_value].append(word)
            self.size += 1
        # Om ordet inte finns med i bucketen i listan med ordets position så
        # lägger den till ordet i bucketen i listan med ordets position.
        # Och så gör lägger den till 1 på self.size.

        if self.size == len(self.buckets):
            self.rehash()
        # Om self.size är samma som längden av bucketen så går den in i
        # funktionen Rehash() som gör bucketen dubbelt så stor.

    # Returns a string representation of the set content
    def to_string(self):
        s = "{ "
        # Gör en varaibel som innehåller strängen "{ "

        for i in self.buckets:
            for j in i:
                s += j + " "
        # För varje lista i bucketen och för varje element i listorna,
        # Så ska den lägga till elementen (namnen) + ett mellanrum
        # till variabeln "s"

        s += "}"
        # Lägger till "}" till varaibeln s

        return s

    # Returns current number of elements in set
    def get_size(self):
        return self.size
        # Skickar tillbaka värdet av self.size

    # Returns True if word in set, otherwise False
    def contains(self, word):
        hash_value = self.get_hash(word)
        # Beräknar ordets (som man skickar in i funktionen) position i bucketen
        # med hjälp av funktionen "get_hash" och sparar det
        # värdet i variabeln has_value

        if word not in self.buckets[hash_value]:
            return False
        else:
            return True
        # Om ordet inte finns med i bucketen i listan med ordets position så
        # returna False, annars returna True.
        # NOTE: Fungerar nästan samma som funktionen Add()

    # Returns current size of bucket list
    def bucket_list_size(self):
        return len(self.buckets)
        # returnar längden av bucketen (Så många listor det finns i bucketen)

    # Removes word from set if there, does nothing if word not in set
    def remove(self, word):
        hash_value = self.get_hash(word)
        # Beräknar ordets (som man skickar in i funktionen) position i bucketen
        # med hjälp av funktionen "get_hash" och sparar det
        # värdet i variabeln has_value

        if word in self.buckets[hash_value]:
            self.buckets[hash_value].remove(word)
            self.size -= 1
        # Om ordet finns med i bucketen i listan med ordets position så
        # tar den bort ordet ur bucketen ur listan med ordets position.
        # Och så tar den bort 1 ifrån self.size.
        # NOTE: Väldigt likt funktionen Add()

    # Returns the size of the bucket with most elements
    def max_bucket_size(self):
        size = 0
        for i in self.buckets:
            if size < len(i):
                size = len(i)
        # Kollar alla listor i bucketen. Listan som är störst kommer att
        # lagra sin längd i variabeln size.
        return size
