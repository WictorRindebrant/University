// C# code
using System;

class Dice {
    private int sides;
    private Random random;

    public Dice(int sides) {
        this.sides = sides;
        random = new Random();
    }

    public int Roll() {
        return random.Next(1, sides + 1);
    }
}

class Player {
    private string name;
    private int score;

    public Player(string name) {
        this.name = name;
        score = 0;
    }

    public void UpdateScore(int points) {
        score += points;
    }

    public string GetName() {
        return name;
    }

    public int GetScore() {
        return score;
    }
}

class MainClass {
    public static void Main(string[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Dice dice = new Dice(6);

        for (int i = 0; i < 10; i++) {
            int roll1 = dice.Roll();
            int roll2 = dice.Roll();

            if (roll1 > roll2) {
                player1.UpdateScore(1);
            } else if (roll1 < roll2) {
                player2.UpdateScore(1);
            }
        }

        Console.WriteLine(player1.GetName() + ": " + player1.GetScore() + " points");
        Console.WriteLine(player2.GetName() + ": " + player2.GetScore() + " points");
    }
}
