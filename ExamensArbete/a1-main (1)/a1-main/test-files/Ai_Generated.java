// Java code
import java.util.Random;

class Dice {
    private int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int roll() {
        Random rand = new Random();
        return rand.nextInt(sides) + 1;
    }
}

class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void updateScore(int points) {
        score += points;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Dice dice = new Dice(6);

        for (int i = 0; i < 10; i++) {
            int roll1 = dice.roll();
            int roll2 = dice.roll();

            if (roll1 > roll2) {
                player1.updateScore(1);
            } else if (roll1 < roll2) {
                player2.updateScore(1);
            }
        }

        System.out.println(player1.getName() + ": " + player1.getScore() + " points");
        System.out.println(player2.getName() + ": " + player2.getScore() + " points");
    }
}
