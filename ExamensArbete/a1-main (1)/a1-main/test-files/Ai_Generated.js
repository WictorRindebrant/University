// JavaScript code
class Dice {
    constructor(sides) {
        this.sides = sides;
    }

    roll() {
        return Math.floor(Math.random() * this.sides) + 1;
    }
}

class Player {
    constructor(name) {
        this.name = name;
        this.score = 0;
    }

    updateScore(points) {
        this.score += points;
    }
}

let player1 = new Player("Player 1");
let player2 = new Player("Player 2");
let dice = new Dice(6);

for (let i = 0; i < 10; i++) {
    let roll1 = dice.roll();
    let roll2 = dice.roll();

    if (roll1 > roll2) {
        player1.updateScore(1);
    } else if (roll1 < roll2) {
        player2.updateScore(1);
    }
}

console.log(player1.name + ": " + player1.score + " points");
console.log(player2.name + ": " + player2.score + " points");
