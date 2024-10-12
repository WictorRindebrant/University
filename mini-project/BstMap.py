from dataclasses import dataclass
from typing import Any

# The BstMap class is a binary search tree based implementation of
# a map (or dictionary). It works for any type of values and for
# all types keys that are comparable ==> we can compare keys using
# the operators < and >.


# The Node class is responsible for most of the work.
# Each call to the BstMap class is just delegated to the
# root node which starts a recursive sequence of calls to
# handle the request. Notice: All Node methods are recursive.
@dataclass
class Node:
    key: Any = None         # the key
    value: Any = None       # the value
    left: Any = None        # left child (a Node)
    right: Any = None       # right child (a Node)

    def put(self, key, value):
        if key == self.key:
            self.value = value
        # Om key (som man skickar in i funktionen ) är samma som self.key så
        # ska den skriva över self.value med value man skickar in i funktionen

        elif key < self.key:
            if self.left is None:
                self.left = Node(key, value, None, None)
            else:
                Node.put(self.left, key, value)
        # Om key (som man skickar in i funktionen är mindre) än self.key.
        # Så kollar den först om self.left är None och isf så lägger den
        # in en ny Node på den platsen med värderna key och value.
        # annars om det finns någonting på den plattsen så kör den om
        # funktionen med den noden som ligger till vänster.

        elif key > self.key:
            if self.right is None:
                self.right = Node(key, value, None, None)
            else:
                Node.put(self.right, key, value)
        # Om key (som man skickar in i funktionen) är mindre än self.key.
        # Så kollar den först om self.right är None och isf så lägger den
        # in en ny Node på den platsen med värderna key och value.
        # annars om det finns någonting på den plattsen så kör den om
        # funktionen med den noden som ligger till höger.

    def to_string(self):
        elements = ""

        if self.left:
            elements += Node.to_string(self.left)
        # Om det finns någon node vänster om noden som körs, så körs funktionen
        # med den noden som är till vänster. Och för varje gång man kör
        # funktionen så lägger man till variabeln som man returnar på elements
        # NOTE: Även om man kör om funktionen med self.left så kommer
        # fortfarande allt som står här under i funktionen att köras.

        elements += "(" + self.key + "," + str(self.value) + ") "
        # För varje node som körs så gör man en variabel där nodens key och
        # value sparas i.

        if self.right:
            elements += Node.to_string(self.right)
        # Om det finns någon node höger om noden som körs, så körs funktionen
        # med den noden som är till höger. Och för varje gång man kör
        # funktionen så lägger man till variabeln som man returnar på elements

        return elements
        # För varje gång man gör funktionen så returnas en sträng av det som
        # man sparat i variablen elements (key och value).

    def count(self):
        Size = 1
        # Man sätter Size till 1 så man kan returna det värdet för varje gång
        # man kör funktionen med en ny node

        if self.left:
            Size += Node.count(self.left)
        # Om det finns någon node vänster om noden som körs, så körs funktionen
        # med den noden som är till vänster. Och för varje gång man kör
        # funktionen så lägger man till variabeln som man returnar på elements
        # NOTE: Även om man kör om funktionen med self.left så kommer
        # fortfarande allt som står här under i funktionen att köras.

        if self.right:
            Size += Node.count(self.right)
        # Om det finns någon node höger om noden som körs, så körs funktionen
        # med den noden som är till höger. Och för varje gång man kör
        # funktionen så lägger man till variabeln som man returnar på "Size"

        return Size
        # För varje gång man gör funktionen så returnas värdet på size
        # NOTE: Fungerar på samma sätt som funktionen "to_string"

    def get(self, key):
        if key == self.key:
            return self.value
        # Om key (som man skickar in i funktionen ) är samma som self.key så
        # ska den returna value på den noden.

        elif key < self.key:
            if self.left:
                return Node.get(self.left, key)
        # Om key (som man skickar in i funktionen är mindre) än self.key.
        # Så kollar den om det finns någonting vänster om noden, om det finns
        # det så kör den om funktionen med den noden som ligger till vänster.

        elif key > self.key:
            if self.right:
                return Node.get(self.right, key)
        # Om key (som man skickar in i funktionen är större) än self.key.
        # Så kollar den om det finns någonting höger om noden, om det finns
        # det så kör den om funktionen med den noden som ligger till höger.
        # NOTE: Fungerar på samma sätt som funktionen "put"

    def max_depth(self):
        left_depth = 0
        right_depth = 0
        # Sätter left_depth och right_depth till 0 varje gång
        # man kör om funktionen.

        if self.left:
            left_depth = self.left.max_depth()
        # Om det finns någon node åt vänster så kör om funktionen med noden
        # som är åt vänster och lagra dens värde till left_depth.

        if self.right:
            right_depth = self.right.max_depth()
        # Om det finns någon node åt höger så kör om funktionen med noden
        # som är åt höger och lagra dens värde till right_depth.

        max = left_depth
        if right_depth > max:
            max = right_depth
        # Sätter variablen max till värdet av left_depth om den har det
        # största värdet annars sätter man varaiblen max till värdet av
        # right_depth om den har det största värdet.

        return max + 1
        # Skickar tillbaka värdet på max plus 1

    def as_list(self, lst):
        if self.left:
            Node.as_list(self.left, lst)
        # Om det finns någon node till vänster om noden som man kör
        # funktionen med så ska den köra funktionen med noden som
        # är till vänster tillsammans med variablen lst.
        # NOTE: Även om man kör om funktionen med self.left så kommer

        if self.key:
            Tuple = (self.key, self.value)
            lst.append(Tuple)
        # Om nodens key inte är None så skapas en Tuple av nodens
        # key och value som sedan läggs till i variabeln lst
        # (som är en lista som man skickat in till funktionen)

        if self.right:
            Node.as_list(self.right, lst)
        # Om det finns någon node till höger om noden som man kör
        # funktionen med så ska den köra funktionen med noden som
        # är till höger tillsammans med variablen lst.

        return lst
        # För varje gång man kör funktionen så returnas listan lst
        # NOTE: Funkar nästan på samma sätt som "to_string"

# The BstMap class is rather simple. It basically just takes care
# of the case when the map is empty. All other cases are delegated
# to the root node ==> the Node class.
#
# The class below is complete ==> not to be changed


@dataclass
class BstMap:
    root: Node = None

    # Adds a key-value pair to the map
    def put(self, key, value):
        if self.root is None:    # Empty, add first node
            self.root = Node(key, value, None, None)
        else:
            self.root.put(key, value)

    # Returns a string representation of all the key-value pairs
    def to_string(self):
        if self.root is None:     # Empty, return empty brackets
            return "{ }"
        else:
            res = "{ "
            res += self.root.to_string()
            res += "}"
            return res

    # Returns the current number of key-value pairs in the map
    def size(self):
        if self.root is None:
            return 0
        else:
            return self.root.count()

    # Returns the value for a given key. Returns None
    # if key doesn't exist (or map is empty)
    def get(self, key):
        if self.root is None:
            return None
        else:
            return self.root.get(key)

    # Returns the maximum tree depth. That is, the length
    # (counted in nodes) of the longest root-to-leaf path
    def max_depth(self):
        if self.root is None:
            return 0
        else:
            return self.root.max_depth()

    # Returns a sorted list of all key-value pairs in the map.
    # Each key-value pair is represented as a tuple and the
    # list is sorted on the keys ==> left-to-right in-order
    def as_list(self):
        lst = []
        if self.root is None:
            return lst
        else:
            return self.root.as_list(lst)
