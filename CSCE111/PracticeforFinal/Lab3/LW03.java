import java.util.Scanner;

public class LW03 {

	public static double approx_sin(double x) {
		double val = x - (Math.pow(x,3) / 6) + (Math.pow(x,5) / 120) - (Math.pow(x,7) / 5040);
		return val;
	}

	public static double triangleArea(double a, double  b, double c) {
		double s = (a + b + c) / 2;
		double area = Math.sqrt(s * (s-a) * (s-b) * (s-c));
		return area;
	}

	public static void triangle() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Side 1: ");
		double side1 = scanner.nextDouble();
		System.out.print("Enter Side 2: ");
		double side2 = scanner.nextDouble();
		System.out.print("Enter Side 3: ");
		double side3 = scanner.nextDouble();

		System.out.println("Area of Triangle is: " + triangleArea(side1,side2,side3));
	}



}

