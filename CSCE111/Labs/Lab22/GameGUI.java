
import javax.swing.JFrame;

/**
 * GameGUI class to set up the main game window.
 */
public class GameGUI extends JFrame{
    public SnakeGame game;

    /**
     * Constructor to initialize the game GUI.
     */
    public GameGUI() {
        setTitle("Snake Game");
        setSize(GameConstants.WIDTH * DisplayParameters.CELL_SIZE, GameConstants.HEIGHT * DisplayParameters.CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game = new SnakeGame();
        DisplayPanel panel = new DisplayPanel(game);
        add(panel);
    }

    public static void main(String[] args) {
        GameGUI ui = new GameGUI();
        ui.setVisible(true);
    }
}
