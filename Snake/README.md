# Snake Game

## Description
The Snake Game is a classic and addictive arcade game where the player controls a snake to eat fruits and grow in size. The goal is to achieve the highest possible score without colliding with walls or the snake's own body.

## Features
Intuitive and responsive controls.

[//]: <> ( Increasing difficulty as the snake grows longer. )
Fun and challenging gameplay for all ages.
How to Play
Starting the Game

## Launch the game application.
[//]: <> (The game starts on the main menu screen.)
[//]: <> (Main Menu)

[//]: <> (Click the "Play" button to start the game.)
Use the arrow keys (or designated controls) to control the snake's direction.

## Gameplay

- Move the snake to eat the fruit that appears on the screen.
- Each fruit eaten increases the snake's length and score.
- Avoid colliding with walls or the snake's own body, as it will end the game.
- Game Over
    -  The game ends when the snake collides with a wall or itself.
    -  The final score is displayed on the game over screen.
The game ends when the snake collides with a wall or itself.
The final score is displayed on the game over screen.

[//]: <> (Restart)

I believe that all the previously proposed solutions (apart from those that require specific implementations) result in the comments being included in the output HTML, even if they are not displayed.

If you want a comment that is strictly for yourself (readers of the converted document should not be able to see it, even with "view source") you could (ab)use the link labels (for use with reference style links) that are available in the core Markdown specification:

http://daringfireball.net/projects/markdown/syntax#link

That is:

[comment]: <> (This is a comment, it will not be included)
[comment]: <> (in  the output file unless you use it in)
[comment]: <> (a reference style link.)
Or you could go further:

[//]: <> (After a game over, click the "Play Again" button to start a new game.)
