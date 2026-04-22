import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * SnakeGame class managing the overall game state.
 * It handles the snake, apple, and game timer.
 */
public class SnakeGame implements ActionListener{
	public  Snake snake;
	public  Apple apple;

	/**
	 * Constructor to initialize the SnakeGame.
	 */
	public SnakeGame() {
		// TODO: Initialize the snake, using the private createNewApple 
		// method to place the first apple. Start a game timer with 200ms delay.
		// The game object should handle timer events.

	}

	/**
	 * Handle user input to change the snake's direction.
	 * @param direction The new direction (0=up, 1=right, 2=down, 3=left)
	 * Use GameConstants for direction values.
	 */
	public void userInput(int direction) {
		// TODO: Update the snake's direction based on user input
		
	}	

	/**
	 * Create a new apple at a random position not occupied by the snake.
	 */
	private void createNewApple() {
		// TODO: Create a new apple at a random position
		// Ensure the apple does not spawn on the snake's body
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: This method is called on each timer tick.
		// Move the snake. If it collides with itself, 
		// end the game (e.g., print "Game Over" and exit).
		// If the snake eats the apple, increase its length.

	}

    @Override
    public String toString() {
		String out = "SnakeGame State:\n";
		out += "Snake: " + snake.toString() + "\n";
		out += "Snake Direction: " + snake.direction + "\n";
		out += "Apple: (" + apple.x + ", " + apple.y + "), Value: " + apple.value + "\n\n";
        return out;
    }

}