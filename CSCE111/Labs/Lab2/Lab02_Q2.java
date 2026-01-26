import java.util.Scanner;

public class Lab02_Q2 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to Interest Calculator");
        System.out.println("------------------------------");
        System.out.print("Enter the Principal amount: ");
        double principal = console.nextDouble();
        System.out.println("Enter the Annualized interest Rate (e.g., 5 for 5%):");
        double interestRate = console.nextDouble();

        double firstMonthInterest = (principal * interestRate) / (12*100);
        System.out.println("--- Month 1 ---");
        System.out.println("Interest accrued: " + firstMonthInterest);
        double totalOwed = firstMonthInterest + principal;
        System.out.println("Total amount owed: " + (totalOwed));
        System.out.println("How much would you like to pay?");
        double firstMonthPayment = console.nextDouble();

        totalOwed -= firstMonthPayment;

        System.out.println("--- Month 2 ---");
        double secondMonthInterest = (totalOwed * interestRate) / (12*100);

        System.out.println("Interest accrued: " + secondMonthInterest);
        totalOwed+= secondMonthInterest;
        System.out.println("Total amount owed: " + (totalOwed));
        System.out.println("How much would you like to pay?");
        double secondMonthPayment = console.nextDouble();

        totalOwed -= secondMonthPayment;

        System.out.println("--- Month 3 ---");
        double thirdMonthInterest = (totalOwed * interestRate) / (12*100);

        System.out.println("Interest accrued: " + thirdMonthInterest);
        totalOwed+= thirdMonthInterest;
        System.out.println("Total amount owed: " + (totalOwed));
        System.out.print("How much would you like to pay? ");
        double thirdMonthPayment = console.nextDouble();

        totalOwed -= thirdMonthPayment;
        System.out.println("------------------------------");

        System.out.println("Remaining balance after 3 months: " + totalOwed);




        


        

        console.close();
    }
}
