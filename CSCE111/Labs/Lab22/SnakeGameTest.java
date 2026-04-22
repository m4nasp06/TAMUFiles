import java.awt.Point;

public class SnakeGameTest {
    public static void main(String[] args) throws InterruptedException {
        SnakeGame game = new SnakeGame();

        while (true) { 
            Thread.sleep(500); // Pause for half a second between moves
            Point applePosition = new Point(game.apple.x, game.apple.y);
            Point snakeHead = game.snake.snake.get(0);
            if (snakeHead.x < applePosition.x) {
                game.userInput(GameConstants.RIGHT);
            } else if (snakeHead.x > applePosition.x) {
                game.userInput(GameConstants.LEFT);
            } else if (snakeHead.y < applePosition.y) {
                game.userInput(GameConstants.DOWN);
            } else if (snakeHead.y > applePosition.y) {
                game.userInput(GameConstants.UP);
            }
            System.out.println(game);
        }
    }
}
