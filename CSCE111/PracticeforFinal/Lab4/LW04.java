import java.util.Scanner;

public class LW04 {

	

	public static void timeConversion(int seconds) {
		int minutes = seconds / 60;
		int remainder_seconds = seconds % 60;

		int hours = minutes / 60;
		int remainder_minutes = minutes % 60;
		System.out.println(seconds + " seconds = " + hours + " hours, " + remainder_minutes + " minutes and " + remainder_seconds + " seconds");

	}

	public static int nth_digit(int x , int n) {
		x /= Math.pow(10,n);
		x %= 10;
		return x;
	}

	public static int reverse_digits(int x) {
		int digit1 = nth_digit(x , 0);
		int digit2 = nth_digit(x , 1);
		int digit3 = nth_digit(x , 2);
		int digit4 = nth_digit(x , 3);

		digit1 *= 1000;
		digit2 *= 100;
		digit3 *= 10;
		digit4 *= 1;
		return digit1 + digit2 + digit3 + digit4;

	}

	public static void reverse_digits() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number < 9999: ");
		int num = scanner.nextInt();
		System.out.print("Number with reversed digits is: " + reverse_digits(num));

	}

	public static int fair_die() {
		int prob = (int) (Math.random() * 6.0) + 1;
		return prob;
	}
}