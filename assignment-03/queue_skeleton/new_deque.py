from dataclasses import dataclass
from typing import Any


@dataclass
class Node:
    value: int = None
    nxt: Any = None


@dataclass
class Deque:
    head: Node = None
    tail: Node = None
    size: int = 0

    def add_first(self, n):
        if self.head is None:
            self.head = Node(n, None)
        # Om värdet på self.head är None (Om man inte lagt till någon Node än)
        # Så kommer den att lägga in en node med value "värdet man skickar in
        # till funktionen" och nxt kommer att vara None.

            self.size += 1
            # I varje funktion där vi lägger till en Node så måste vi lägga
            # till 1 på self.size

        else:
            self.head = Node(n, self.head)
        # Om det redan finns en eller flera Nodes på self.head så kommer man
        # att skapa en ny node som har value "värdet man skickar in till
        # funktionen" och nxt kommer att vara allt som finns i self.head

            self.size += 1
            # I varje funktion där vi lägger till en Node så måste vi lägga
            # till 1 på self.size

    def to_string(self):
        string = "{ "

        node = self.head
        while node is not None:
            string += str(node.value) + " "
            node = node.nxt
        # Om varaiben "node" inte är None så sparar den värdet av "self.head"s
        # value till variabeln "string" som vi skapade i början av funktionen.
        # Sen så sätter vi värdet på varaibeln node till värdet av node.next
        # och lägger till den nodens value till variabeln "string". Vi gör
        # detta tills varaibeln får värdet None

        node = self.tail
        while node is not None:
            string += str(node.value) + " "
            node = node.nxt
        string += "}"
        # Vi gör precis samma som ovan bara att vi gör det med self.tail
        # istället för self.head. Så att vi får ut alla värden ifrån båda
        # dequesen

        return string
        # Tillsist så returnar vi allt som vi samlat på oss igenom funktionen.

    def add_last(self, n):
        if self.tail is None:
            self.tail = Node(n, None)
        # Om värdet på self.tail är None (Om man inte lagt till någon Node än)
        # Så kommer den att lägga in en node med value "värdet man skickar in
        # till funktionen" och nxt kommer att vara None.

            self.size += 1
            # I varje funktion där vi lägger till en Node så måste vi lägga
            # till 1 på self.size

        else:
            node = self.tail
            while node.nxt is not None:
                node = node.nxt
            node.nxt = Node(n, None)
            # Om värdet på "node.nxt" inte är None så kommer den att skriva
            # över värdet på node med node.nxt (så att man rör sig åt höger i
            # dequen "tail") tills man kommer till sista noden i dequen
            # som har sitt nxt värde satt som None. När man hittar den
            # noden så skriver man över dens nxt värde (None) med en ny
            # node som har värdet av det man skickade in i funktionen och med
            # sitt nxt värde som None.

            self.size += 1
            # I varje funktion där vi lägger till en Node så måste vi lägga
            # till 1 på self.size

    def get_last(self):
        if self.tail is None:
            print("You can't access an empty queue")
            return None
            # If there is no Node in self.tail, then print error message
            # and return None.

        node = self.tail
        while node.nxt is not None:
            node = node.nxt
        return node.value
        # Om värdet på "node.nxt" inte är None så kommer den att skriva
        # över värdet på node med node.nxt (så att man rör sig åt höger i
        # dequen "tail") tills man kommer till sista noden i dequen
        # som har sitt nxt värde satt som None. När man hittar den
        # noden så returnar man den då det är den sista Noden i dequen

    def get_first(self):
        if self.head is None:
            print("You can't access an empty queue")
            return None
            # If there is no Node in self.head, then print error message
            # and return None.

        return self.head.value
        # Returnar första värdet på self.head (som är första Noden i dequen)

    def remove_first(self):
        if self.size == 1:
            self.tail = None
            self.head = None
            self.size = 0
            return None
            # Om funktionen körs och self.size bara är 1 så tar man bort alla
            # Nodes ifrån self.tail och self.head och sätter self.size till 0
            # och returnar None

        if self.head is None:
            if self.tail:
                removed = self.tail.value
                self.tail = self.tail.nxt
                # Om self.head deuqen är tom och om self.tail inte är tom så
                # sparars värdet på den första Noden i self.head i en variabel
                # Sen skriver man över self.head
                # med allt som kommer efter den första Noden i self.head.

                self.size -= 1
                # I varje funktion där vi tar bort en Node så måste vi ta bort
                # 1 på self.size

                return removed
                # Returnar värdet på Noden som man tar bort
            else:
                print("You can't access an empty queue")
                return None
                # If there is no Node in self.head, then print error message
                # and return None.

        if self.head:
            removed = self.head.value
            self.head = self.head.nxt
            # Sparar värdet på den första Noden i self.head i en variabel
            # Sen skriver man över self.head
            # med allt som kommer efter den första Noden i self.head.

            self.size -= 1
            # I varje funktion där vi tar bort en Node så måste vi ta bort
            # 1 på self.size

            return removed
            # Returnar värdet på Noden som man tar bort

    def remove_last(self):
        if self.size == 1:
            self.tail = None
            self.head = None
            self.size = 0
            return None
            # Om funktionen körs och self.size bara är 1 så tar man bort alla
            # Nodes ifrån self.tail och self.head och sätter self.size till 0
            # och returnar None

        if self.tail is None:
            if self.head:
                node = self.head
                while node.nxt is not None:
                    Last = node
                    node = node.nxt
                Last.nxt = None
                # Om self.tail deuqen är tom och om self.head inte är tom och
                # Om värdet på "node.nxt" inte är None så kommer den att skriva
                # över värdet på node med node.nxt (så att man rör sig åt
                # höger i dequen "tail") tills man kommer till sista noden i
                # dequen som har sitt nxt värde satt som None. När man hittar
                # den noden så vet man att det är den sista Noden, men för att
                # få bort den sista noden så måste man byta ut nxt värdet på
                # den näst sista noden till None, och för att hitta den näst
                # sista noden så sparar man bara ett värde till värdet på
                # noden innan man sätter node till node.nxt värdet. På så sätt
                # så kommer man ha sista noden och näst sista noden lagrade
                # när while loopen tar slut. och då är det bara att skriva
                # över näst sista noden nxt till None

                self.size -= 1
                # I varje funktion där vi tar bort en Node så måste vi ta bort
                # 1 på self.size

                return node.value
                # Returnar värdet på Noden som man tar bort
            else:
                print("You can't access an empty queue")
                return None
                # If there is no Node in self.tail, then print error message
                # and return None.

        if self.tail:
            node = self.tail
            while node.nxt is not None:
                Last = node
                node = node.nxt
            Last.nxt = None
            # Om värdet på "node.nxt" inte är None så kommer den att skriva
            # över värdet på node med node.nxt (så att man rör sig åt höger i
            # dequen "tail") tills man kommer till sista noden i dequen
            # som har sitt nxt värde satt som None. När man hittar den
            # noden så vet man att det är den sista Noden, men för att få bort
            # den sista noden så måste man byta ut nxt värdet på den näst sista
            # noden till None, och för att hitta den näst sista noden så sparar
            # man bara ett värde till värdet på noden innan man sätter node
            # till node.nxt värdet. På så sätt så kommer man ha sista noden
            # och näst sista noden lagrade när while loopen tar slut.
            # och då är det bara att skriva över näst sista noden nxt till None

            self.size -= 1
            # I varje funktion där vi tar bort en Node så måste vi ta bort
            # 1 på self.size

            return node.value
            # Returnar värdet på Noden som man tar bort
