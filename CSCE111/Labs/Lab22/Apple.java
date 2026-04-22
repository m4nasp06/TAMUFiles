/* 
Apple.java will be used to create apples in the game. 
It has three attributes and a constructor. 
The attributes are already defined for you. For the constructor.
 You must initialize the x and y coordinates of the apple object randomly. 
 Use Math.random to generate the random numbers between 0 and the grid limit. 
 The value for the apple represents the increase in length of snake from eating the apple.
  You can set it to a small constant, e.g. 5,  to start with but you can also change it later if you want to implement features like bonus apple,
   or a length reduction apple. 
*/

public class Apple {
	public int x;
	public int y;
	public int value = 5;

	public Apple() {
		// TODO: Initialize the apple at a random position
		this.x = (int)(Math.random() * GameConstants.WIDTH);
		this.y = (int)(Math.random() * GameConstants.HEIGHT);
	}
}