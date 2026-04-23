import java.awt.Point;
import java.util.ArrayList;

/**
 * Snake class representing the snake in the game.
 * It manages the snake's position, direction, and movement.
 */
public class Snake {
	public int direction;
	public int lastDirection;
    public int length;
	public ArrayList<Point> snake = new ArrayList<>();

    /**
     * Constructor to initialize the snake.
     */
    public Snake() {
        // TODO: Initialize the snake in the center of the screen, 
        // moving to the right, with a length of 5
        length = 5;
        direction = GameConstants.RIGHT;
        lastDirection = GameConstants.RIGHT;
        Point head = new Point(GameConstants.WIDTH / 2, GameConstants.HEIGHT / 2);
        snake.add(head);

    }

    /**
     * Set the direction of the snake.
     * @param direction The new direction (0=up, 1=right, 2=down, 3=left)
     * Use GameConstants for direction values.
     */
    public void set_direction(int direction) {
        // TODO: Update the snake's direction, 
        // ensuring it cannot reverse onto itself
        // up is 0, down is 1, left is 2, right is 3
        if ((lastDirection + direction) == 1 || (lastDirection + direction) == 5) {
            return;
        }
        this.direction = direction;
    }

    /**
     * Move the snake in the current direction.
     * Update the snake's position and handle screen wrapping.
     * @throws RuntimeException if the snake collides with itself.
     */
	public void move() {
        // TODO: Move the snake in the current direction,
        // updating its position. Wrap around the screen 
        // edges when the snake goes off-screen. 
        // Check that the snake does not collide with itself.
        // If the snake collides with itself, throw a RuntimeException.
        lastDirection = direction;
        Point nextHead = new Point();
        // up is 0, down is 1, left is 2, right is 3
        if (direction == GameConstants.UP) {
            nextHead.x = snake.get(0).x;
            nextHead.y = snake.get(0).y - 1;
        }
        if (direction == GameConstants.DOWN) {
            nextHead.x = snake.get(0).x;
            nextHead.y = snake.get(0).y + 1;
        }
        if (direction == GameConstants.RIGHT) {
            nextHead.x = snake.get(0).x + 1;
            nextHead.y = snake.get(0).y;
        }
        if (direction == GameConstants.LEFT) {
            nextHead.x = snake.get(0).x - 1;
            nextHead.y = snake.get(0).y;
        }
        
        // wrap around the screen edges
        if (nextHead.x < 0) {
            nextHead.x = GameConstants.WIDTH - 1;
        }
        if (nextHead.x >= GameConstants.WIDTH) {
            nextHead.x = 0;
        }
        if (nextHead.y < 0) {
            nextHead.y = GameConstants.HEIGHT - 1;
        }
        if (nextHead.y >= GameConstants.HEIGHT) {
            nextHead.y = 0;
        }

        // update body
        snake.add(0, nextHead);
        if (snake.size() > length) {
            snake.remove(snake.size() - 1);
        }

        // collision
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).equals(nextHead)) {
                throw new RuntimeException("Game Over: Snake collided with itself!");
            }
        }

        
    
    
    }

    /** 
     * Returns a string representation of the snake's body.
     */
    @Override
    public String toString() {
        String out = "";
        for (Point part : snake) {
            out += "(" + part.x + "," + part.y + ") ";
        }
        return out;
    }
    
}