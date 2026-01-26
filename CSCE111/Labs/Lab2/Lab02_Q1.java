
import java.util.Scanner;

public class Lab02_Q1 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println();
        System.out.print("Enter radius of Circle: ");
        double radius = console.nextDouble();
        double circumference = 3.142 * (radius) * 2;
        System.out.println("Circumference of Circle: " + circumference);

        System.out.println();

        System.out.print("Enter side of Square: ");
        double side = console.nextDouble();
        double perimeter = side * 4;
        System.out.println("Perimeter of Square: " + perimeter);

        System.out.println();

        double side1, side2, side3;
        System.out.print("Enter side1 of Triangle: ");
        side1 = console.nextDouble();
        System.out.print("Enter side2 of Triangle: ");
        side2 = console.nextDouble();
        System.out.print("Enter side3 of Triangle: ");
        side3 = console.nextDouble();
        double trianglePerimeter = side1 + side2 + side3;
        System.out.println("Perimeter of Triangle: " + trianglePerimeter);
        System.out.println();

        System.out.println("Good Bye!");

        console.close();
    }
}
