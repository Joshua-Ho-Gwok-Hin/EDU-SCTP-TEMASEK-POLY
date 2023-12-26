const prompt = require('prompt-sync')({ sigint: true });

const hat = '^';
const hole = 'O';
const fieldCharacter = '░';
const pathCharacter = '*';

class Field {
    constructor(array2D, level = 1) {
        this._array2D = array2D;
        this._level = level;
        this._holes = Math.floor((this._level * 5) + this._array2D.length * 1.5);
        this._currentRow = 0;
        this._currentCol = 0;
        this._gameOver = false;
        this._message = '';
    }

    // To assign some 'O' relative to this._level
    setupHolesOfAbyss() {
        let count = 0;
        while (count < this._holes) {
            const row = Math.floor(Math.random() * this._array2D.length);
            const col = Math.floor(Math.random() * this._array2D.length);
            let assignedPosition = this._array2D[row][col];
            if ((assignedPosition !== hole) && (assignedPosition !== hat) && (assignedPosition !== this._array2D[0][0])) {
                this._array2D[row][col] = hole;
                count++;
            }
        }
    }

    #print() {
        this.#printHeader();
        // this.#testingMethod() is for testing purposes. 
        // To test, uncomment L37, comment-out L39, L40 & newGame.setupHolesOfAbyss() at the bottom
        // this.#testingMethod(); 
        this.#printField();
        let input = prompt("Which way? (u/d/l/r): ");
        this.#direction(input);
        console.log(`Current position: R${this._currentRow + 1}, C${this._currentCol + 1}.`);
        // Added try and catch block to continue the game when this._currentRow = -1
        try {
            process.stdout.write(this._array2D[this._currentRow][this._currentCol] + ' ');
        } catch { };
        if (this._message) {
            console.log(this._message);
            this._message = '';
        }
    }

    #printHeader() {
        console.log('\n' + "*".repeat(this._array2D.length * 2 + 4));
        console.log(`Find Your Hat - Level ${this._level}, ${this._array2D.length * this._array2D[0].length} (${this._array2D.length} x ${this._array2D[0].length}) Grids`);
        console.log("*".repeat(this._array2D.length * 2 + 4));
        console.log(`Space: ${fieldCharacter}${fieldCharacter} Controls : u d l r  Level: ${this._level}`);
        console.log(`Path : ** KeyStroke:⬆️ ⬇️ ⬅️ ➡️   Abyss: ${this._holes} x OO\n`);
    }

    #printField() {
        for (let row = 0; row < this._array2D.length; row++) {
            if (row < 9) {
                process.stdout.write(' ' + (row + 1) + '  ');
            } else {
                process.stdout.write((row + 1) + '  ')
            }
            for (let col = 0; col < this._array2D[row].length; col++) {
                process.stdout.write(`${this._array2D[row][col]}${this._array2D[row][col]}`);
            }
            process.stdout.write('\n');
        }
    }

    // Method for testing responses before adding prompt sync. 
    // Navigating to manually added 'O', '^' and also out-of-bound this._currentRow/_currentCol values e.g. -1, 20
    #testingMethod() {
        // this.direction('r');
        // this.direction('r'); this.direction('r'); this.direction('r');
        // this.direction('r'); this.direction('r'); this.direction('r'); this.direction('r'); this.direction('r');
        // this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d');
        // this.direction('l'); this.direction('l'); this.direction('l'); this.direction('l');
        // this.direction('u'); this.direction('u'); this.direction('u');
        // this.direction('l');
        // this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d');
        // this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d');
        // this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d'); this.direction('d');
        // this.direction('d'); this.direction('d');
        // this.direction('r'); this.direction('r'); this.direction('r'); this.direction('r'); this.direction('r');
        // this.direction('r'); this.direction('r'); this.direction('r'); this.direction('r'); this.direction('r');
        // this.direction('r'); this.direction('r'); this.direction('r'); this.direction('r');
        // this.direction('r'); this.direction('r');
    }

    // input parameter is for prompt-sync arg
    #direction(input) {
        if (typeof input !== 'string') {
            console.log("Please enter u, d, l or r");
        } else {
            input = input.toLowerCase();
            switch (input) {
                case "r": {
                    this._array2D[this._currentRow][this._currentCol++];
                    this.#checkStatus();
                    break;
                };
                case "d": {
                    this._array2D[this._currentRow++][this._currentCol];
                    this.#checkStatus();
                    break;
                }
                case "l": {
                    this._array2D[this._currentRow][this._currentCol--];
                    this.#checkStatus();
                    break;
                }
                case "u": {
                    this._array2D[this._currentRow--][this._currentCol];
                    this.#checkStatus();
                    break;
                }
            }
        }
    }

    // The method that print out the field after every 'turns' until the this._gameOver === true
    // To call this class from another <file name>.js
    play() {
        while (!this._gameOver) {
            this.#print()
        }
    }

    // Check the state of the properties of this Field() object after each user input, so as to print out the right response
    #checkStatus() {
        if (this._currentRow === (this._array2D.length -1) && this._currentCol === (this._array2D[0].length -1)) {
            if (this._level < 10) {
                this._gameOver = true;
                this._message = `Nice! You have completed level ${this._level}!`
                this._level++;
            } else {
                this._gameOver = true;
                this._message = "Congratulations! You have completed all levels of the game!";
            }
        } else {
            // To handle error caused by setting this._currentRow = -1
            try {
                switch (this._array2D[this._currentRow][this._currentCol]) {
                    case fieldCharacter: {
                        this._array2D[this._currentRow][this._currentCol] = pathCharacter;
                        break;
                    }
                    case hole:
                    default: {
                        this._gameOver = true;
                        this._message = "You lost, please try again."
                    }
                }
                // If this._currentRow = -1, Catch bug error to continue game.
            } catch {
                this._gameOver = true;
                this._message = "You lost, please try again."
            }
        }
    }
}

module.exports.Field;

// To initialise a square 2D array with a length as parameter. gameFieldArea(12) will get 12 * 12 grids.
const gameFieldArea = (grid) => {
    const array2D = new Array(grid);
    for (let row = 0; row < array2D.length; row++) {
        array2D[row] = new Array(grid);
        for (let col = 0; col < array2D[row].length; col++) {
            array2D[row][col] = fieldCharacter;
        }
    }
    // Assign '*' value to top-left-corner of array2D
    array2D[0][0] = pathCharacter;

    // Assign '^' value to bottom-right-corner of array2D
    array2D[grid - 1][grid - 1] = hat;
    return array2D;
}

// Initialize fields with size as parameter
const newGameField = gameFieldArea(20);

// Instantiate a new Field(arr2D, setLevel)
const newGame = new Field(newGameField, 1);

// Randomly set holes based on this._level
newGame.setupHolesOfAbyss();

// Start game with play method that do a while loop;
newGame.play();