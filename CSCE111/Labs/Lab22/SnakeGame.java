import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * SnakeGame class managing the overall game state.
 * It handles the snake, apple, and game timer.
 */
public class SnakeGame implements ActionListener{
	public Snake snake;
	public Snake snake2;
	public Apple apple;
	public Timer timer;

	/**
	 * Constructor to initialize the SnakeGame.
	 */
	public SnakeGame() {
		snake = new Snake();

		snake2 = new Snake();
		snake2.snake.set(0, new Point(GameConstants.WIDTH * 3 / 4, GameConstants.HEIGHT / 2));
		snake2.direction = GameConstants.LEFT;

		createNewApple();

		timer = new Timer(200, this);
		timer.start();
	}

	/**
	 * Handle user input to change the snake's direction.
	 * @param direction The new direction (0=up, 1=right, 2=down, 3=left)
	 * Use GameConstants for direction values.
	 */
	public void userInput(int direction) {
		snake.set_direction(direction);
	}

	/**
	 * Handle user input for player 2.
	 * @param direction The new direction (0=up, 1=right, 2=down, 3=left)
	 */
	public void userInput2(int direction) {
		snake2.set_direction(direction);
	}

	/**
	 * Create a new apple at a random position not occupied by the snake.
	 */
	private void createNewApple() {
		apple = new Apple();
		boolean conflict = true;
		while (conflict) {
			conflict = false;
			for (Point p : snake.snake) {
				if (apple.x == p.x && apple.y == p.y) {
					apple = new Apple();
					conflict = true;
					break;
				}
			}
			if (!conflict) {
				for (Point p : snake2.snake) {
					if (apple.x == p.x && apple.y == p.y) {
						apple = new Apple();
						conflict = true;
						break;
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			snake.move();
		} catch (RuntimeException ex) {
			System.out.println("Player 1 Game Over! Score: " + snake.length);
			System.exit(0);
		}

		try {
			snake2.move();
		} catch (RuntimeException ex) {
			System.out.println("Player 2 Game Over! Score: " + snake2.length);
			System.exit(0);
		}

		if (snake.snake.get(0).x == apple.x && snake.snake.get(0).y == apple.y) {
			snake.length += apple.value;
			createNewApple();
		}

		if (snake2.snake.get(0).x == apple.x && snake2.snake.get(0).y == apple.y) {
			snake2.length += apple.value;
			createNewApple();
		}
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
