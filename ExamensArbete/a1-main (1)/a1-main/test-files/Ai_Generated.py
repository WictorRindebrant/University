import random

class Dice:
    def __init__(self, sides=6):
        self.sides = sides
    
    def roll(self):
        return random.randint(1, self.sides)

class Player:
    def __init__(self, name):
        self.name = name
        self.score = 0
    
    def update_score(self, points):
        self.score += points

def main():
    player1 = Player("Player 1")
    player2 = Player("Player 2")
    dice = Dice()

    for _ in range(10):
        roll1 = dice.roll()
        roll2 = dice.roll()

        if roll1 > roll2:
            player1.update_score(1)
        elif roll1 < roll2:
            player2.update_score(1)
    
    print(f"{player1.name}: {player1.score} points")
    print(f"{player2.name}: {player2.score} points")

if __name__ == "__main__":
    main()
