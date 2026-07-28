
import java.util.Scanner;


public class Lab02_Q1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome!");
		System.out.println("");

		System.out.print("Enter radius of Circle: ");
		double rad = scanner.nextDouble();
		double circ = 2 * 3.142 * rad;
		System.out.println("Circumference of Circle: " + circ);

		System.out.print("Enter side of Square: ");
		double length = scanner.nextDouble();
		System.out.println("Perimeter of Square: " + length * 4);

		System.out.print("Enter side1 of Triangle: ");
		double s1 = scanner.nextDouble(); 

		System.out.print("Enter side2 of Triangle: ");
		double s2 = scanner.nextDouble(); 

		System.out.print("Enter side3 of Triangle: ");
		double s3 = scanner.nextDouble(); 

		System.out.println("Perimeter of Triangle: " + (s1+s2+s3));

		System.out.println("");
		System.out.println("Good Bye!");

	}

}