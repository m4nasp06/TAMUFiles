import java.util.Scanner;


public class Lab02_Q2{

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Interest Calculator");
		System.out.println("------------------------------");

		System.out.print("Enter the Principal amount: ");
		double princ = scanner.nextDouble();

		System.out.print("Enter the Annualized interest rate (e.g., 5 for 5%): ");
		double rate = scanner.nextDouble();

		double interest = (princ * rate) /  (12 * 100);
		double owed = princ + interest;

		System.out.println();
		System.out.println("--- Month 1 ---");
		System.out.println("Interest accrued: " + interest);
		System.out.println("Total amount owed: " + owed);
		System.out.print("How much would you like to pay?");
		double pay = scanner.nextDouble();

		owed -= pay;

		interest = (owed * rate) /  (12 * 100);
		owed += interest;
		System.out.println();
		System.out.println("--- Month 2 ---");
		System.out.println("Interest accrued: " + interest);
		System.out.println("Total amount owed: " + owed);
		System.out.print("How much would you like to pay?");
		pay = scanner.nextDouble();

		owed -= pay;

		interest = (owed * rate) /  (12 * 100);
		owed += interest;
		System.out.println();
		System.out.println("--- Month 3 ---");
		System.out.println("Interest accrued: " + interest);
		System.out.println("Total amount owed: " + owed);
		System.out.print("How much would you like to pay?");
		pay = scanner.nextDouble();

		owed -= pay;
		System.out.println("------------------------------");
		System.out.println("Remaining balance after 3 months: " + owed);

	}

}