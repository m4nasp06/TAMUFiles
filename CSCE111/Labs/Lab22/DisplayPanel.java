import java.awt.Graphics;
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
		// TODO: Initialize the panel with the game instance,
		// set up a timer for repainting every 100ms,
		// and add a key listener for user input.
	}

	/** 
	 * Paint the current game state.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        int cellSize = DisplayParameters.CELL_SIZE;

		// TODO: Draw the background, snake, and apple
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Called on each timer tick
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Handle key presses to control the snake's direction.
		// Use WASD keys for movement and ESC to exit.
		// Spacebar to pause/resume the game.
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}