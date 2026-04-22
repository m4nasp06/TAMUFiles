
public class SnakeTest {
    public static void main(String[] args) {
        Snake snake = new Snake();
        System.out.println("Initial snake: " + snake);

        System.out.println("\nDirection change tests");
        snake.set_direction(GameConstants.LEFT); // Invalid change
        System.out.println("After trying to change direction to LEFT (should remain RIGHT): " + snake.direction);
        snake.set_direction(GameConstants.UP); // Valid change
        System.out.println("After changing direction to UP: " + snake.direction);
        snake.set_direction(GameConstants.DOWN); // Invalid change
        System.out.println("After trying to change direction to DOWN (should remain UP): " + snake.direction);
        snake.set_direction(GameConstants.LEFT); // Valid change
        System.out.println("After changing direction to LEFT: " + snake.direction);
        snake.set_direction(GameConstants.RIGHT); // Invalid change   
        System.out.println("After trying to change direction to RIGHT (should remain LEFT): " + snake.direction);
        snake.set_direction(GameConstants.DOWN); // Valid change
        System.out.println("After changing direction to DOWN: " + snake.direction);

        System.out.println("\nStarting to move right:");
        snake.set_direction(GameConstants.RIGHT);
        snake.move();
        System.out.println(snake);
        System.out.println("\nMoving 4 steps right:");
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        System.out.println(snake);

        System.out.println("\nStarting to move down:");
        snake.set_direction(GameConstants.DOWN);
        snake.move();
        System.out.println(snake);

        System.out.println("\nAfter moving down 4 more steps:");
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        System.out.println(snake);

        System.out.println("\nStarting to move left:");
        snake.set_direction(GameConstants.LEFT);
        snake.move();
        System.out.println(snake);

        System.out.println("\nAfter moving left 4 more steps:");
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        System.out.println(snake);

        System.out.println("\nStarting to move up:");
        snake.set_direction(GameConstants.UP);
        snake.move();
        System.out.println(snake);

        System.out.println("\nAfter moving up 4 more steps:");
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        System.out.println(snake);

         System.out.println("\nStarting to move right again:");
        snake.set_direction(GameConstants.RIGHT);
        snake.move();
        System.out.println(snake);

        System.out.println("\nAfter moving right 4 more steps:");
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        System.out.println(snake);

        System.out.println("\nTesting Right Boundary Wrap-around:");
        int current_x = snake.snake.get(0).x;
        for (int i = 0; i < GameConstants.WIDTH - current_x - 1; i++) {
            snake.move();
        }
        System.out.println(snake);
        snake.move(); // This move should wrap around
        System.out.println(snake);
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        System.out.println(snake);

        System.out.println("\nTesting Up Boundary Wrap-around:");
        snake.set_direction(GameConstants.UP);
        int current_y = snake.snake.get(0).y;
        for (int i = 0; i < current_y; i++) {
            snake.move();
        }
        System.out.println(snake);
        snake.move(); // This move should wrap around
        System.out.println(snake);
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        System.out.println(snake);

        System.out.println("\nTesting left Boundary Wrap-around:");
        snake.set_direction(GameConstants.LEFT);
        current_x = snake.snake.get(0).x;
        for (int i = 0; i < current_x ; i++) {
            snake.move();
        }
        System.out.println(snake);
        snake.move(); // This move should wrap around
        System.out.println(snake);
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        System.out.println(snake);

        System.out.println("\nTesting down Boundary Wrap-around:");
        snake.set_direction(GameConstants.DOWN);
        current_y = snake.snake.get(0).y;
        for (int i = 0; i < GameConstants.HEIGHT - current_y - 1; i++) {
            snake.move();
        }
        System.out.println(snake);
        snake.move(); // This move should wrap around
        System.out.println(snake);
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        snake.move(); // This move should wrap around
        System.out.println(snake);

        System.out.println("\nTesting Self-Collision:");
        snake.move();
        snake.set_direction(GameConstants.LEFT);
        snake.move();
        snake.set_direction(GameConstants.UP);  
        snake.move();
        snake.set_direction(GameConstants.RIGHT);
        System.out.println(snake);
        try {
        snake.move(); // This move should cause collision
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // Final state of the snake
        System.out.println(snake);
        System.out.println("\nAll tests completed.");
    }
}
