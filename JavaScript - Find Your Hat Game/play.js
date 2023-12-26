const Field = require('./start');

class Play {

    constructor(field) {
        this._field = field;
    }

    setupHoles(){
        this._field.setupHolesOfAbyss()
    }

    play() {
        this._field.play()
    }
}



// Initialize fields with size as parameter
const newGameField = gameFieldArea(20);

// Instantiate a new Field(arr2D, setLevel)
const newGame = new Field(newGameField, 2);

// Start game with play method that do a while loop;
const playGame = new Play(newGame);

// Randomly set holes based on this._level
playGame.setupHoles()

// Play the game from another class for better security.
playGame.play();