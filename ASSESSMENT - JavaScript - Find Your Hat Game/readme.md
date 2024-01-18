# Challenge Project: Find Your Hat
Technical Assessment 2 - Generation SG (Jr Full Stack Developer Course)

[Find Your Hat JavaScript Coding Challenge at Codecademy](https://www.codecademy.com/paths/front-end-engineer-career-path/tracks/fecp-javascript-syntax-part-iii/modules/fecp-challenge-project-find-your-hat/projects/find-your-hat)

After copying start.js into your cpu directory, in the same directory do a 'npm init', followed by 'npm install prompt-sync'. You can then run start.js using the command 'node start.js'.

```
npm init

npm install prompt-sync

node start.js
```

Preview:
```
********************************************
Find Your Hat - Level 1, 400 (20 x 20) Grids
********************************************
Space: ░░ Controls : u d l r  Level: 1
Path : ** KeyStroke: ↑ ↓ ← →  Abyss: 35 x OO

 1  ****░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 2  ░░****░░░░OO░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 3  ░░░░****░░░░░░░░OO░░░░░░░░░░OO░░░░░░░░░░
 4  ░░░░░░**░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 5  ░░OO░░**░░OO░░░░░░OO░░░░░░░░░░░░░░░░░░░░
 6  OO░░░░**░░░░OO░░░░░░░░░░░░░░░░OO░░OO░░░░
 7  OO░░░░****░░░░░░░░░░OO░░░░░░░░░░░░░░░░░░
 8  ░░░░░░░░**░░░░░░OOOO░░░░░░░░OO░░░░░░░░░░
 9  ░░░░OO░░**░░░░OO░░░░░░░░░░░░░░OO░░OOOO░░
10  ░░░░░░░░**░░░░░░░░░░░░░░░░░░░░░░OO░░OO░░
11  ░░OO░░░░**░░OO░░░░░░░░░░░░░░OOOO░░░░░░░░
12  ░░░░░░░░**░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
13  ░░░░░░░░**░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
14  ░░░░░░░░****************░░░░OO░░░░░░░░░░
15  OOOO░░░░░░░░░░░░░░░░░░****░░░░░░░░░░░░░░
16  ░░░░░░░░░░░░░░OO░░░░░░░░****░░░░░░░░░░OO
17  ░░OO░░░░░░░░░░░░░░░░░░░░░░****░░░░░░░░░░
18  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░****░░░░░░░░
19  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░******░░░░
20  ░░░░░░░░░░OOOO░░░░░░░░░░OO░░░░░░░░****^^
Which way? (u/d/l/r): r
Current position: R20, C20.
^ Nice! You have completed level 1!
```

#### What did you like about this project?
I find it fun to code the methods for this game to work.

#### What did you struggle with in this project?
Most of the struggle will be to pinpoint where in the code is giving the unexpected behavior. Have to insert several console.log() to find it. Once found, the next step will be to try different code or ways. Along the way, test the new code/solutions. Only then, can move on to the next task.

#### What would make your experience with this assessment better?
I think the gif of the assessment document may be misleading. It could have stated that a new field is to be reprinted after every user input. 

#### What are some of the references and resources you use to help you in this assessment?
I watched a [youtube video](https://youtu.be/q_8Dkzxy_ms?si=unSyr1MSDeUsKXJH) to see how a completed project looks like. I watched the final game play test without the solution (so that I can come out with the solution myself). 
Another reference I used is Bing AI Chat on how to do a Java's System.out.print() 'equivalent' in Node.js, which is the process.stdout.write()

#### [Instructor](https://github.com/thinktinker)'s recommendations after this assessment's review
- [ ] Add functionality to allow user to quit the game.
- [ ] Complete the functionality for players to advance to next level.
- [ ] Enhance user experience by allowing direction keystrokes input.
- [ ] Enhance user experience by accepting more inputs to simulate multiplayer on the same keyboard.
- [ ] Include algorithm to ensure that every generated games are playable.
