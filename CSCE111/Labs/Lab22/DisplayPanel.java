import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * DisplayPanel class to render the game state.
 * It handles drawing the snake and apple, and user input.
 */
public class DisplayPanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	public SnakeGame game;

	/**
	 * Constructor to initialize the display panel.
	 * @param game The SnakeGame instance to display.
	 */
	public DisplayPanel(SnakeGame game) {
		this.game = game;
		setPreferredSize(new java.awt.Dimension(
			GameConstants.WIDTH * DisplayParameters.CELL_SIZE,
			GameConstants.HEIGHT * DisplayParameters.CELL_SIZE));
		setBackground(DisplayParameters.BACKGROUND_COLOR);
		setFocusable(true);
		addKeyListener(this);
		timer = new Timer(DisplayParameters.TIMER_DELAY, this);
		timer.start();
	}

	/**
	 * Paint the current game state.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cellSize = DisplayParameters.CELL_SIZE;

		// Draw player 1 snake
		g.setColor(DisplayParameters.SNAKE_COLOR);
		for (Point p : game.snake.snake) {
			g.fillOval(p.x * cellSize, p.y * cellSize, cellSize, cellSize);
		}

		// Draw player 2 snake
		g.setColor(new Color(0, 220, 80));
		for (Point p : game.snake2.snake) {
			g.fillOval(p.x * cellSize, p.y * cellSize, cellSize, cellSize);
		}

		// Draw apple
		g.setColor(DisplayParameters.APPLE_COLOR);
		g.fillOval(game.apple.x * cellSize, game.apple.y * cellSize, cellSize, cellSize);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Called on each timer tick
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// Player 1: WASD
		if (key == KeyEvent.VK_W) game.userInput(GameConstants.UP);
		else if (key == KeyEvent.VK_S) game.userInput(GameConstants.DOWN);
		else if (key == KeyEvent.VK_A) game.userInput(GameConstants.LEFT);
		else if (key == KeyEvent.VK_D) game.userInput(GameConstants.RIGHT);

		// Player 2: Arrow keys
		else if (key == KeyEvent.VK_UP) game.userInput2(GameConstants.UP);
		else if (key == KeyEvent.VK_DOWN) game.userInput2(GameConstants.DOWN);
		else if (key == KeyEvent.VK_LEFT) game.userInput2(GameConstants.LEFT);
		else if (key == KeyEvent.VK_RIGHT) game.userInput2(GameConstants.RIGHT);

		// Pause / resume with Space
		else if (key == KeyEvent.VK_SPACE) {
			if (game.timer.isRunning()) game.timer.stop();
			else game.timer.start();
		}

		// Exit with Escape
		else if (key == KeyEvent.VK_ESCAPE) System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
