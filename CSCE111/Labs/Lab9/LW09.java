import java.util.Scanner;

class LW09 {

    public static void main(String[] args) {
        
        factorials();
    }

    public static void factorialDriver() {
        Scanner scnr = new Scanner(System.in);

        int num;

        System.out.print("Enter Number : ");
        num = scnr.nextInt();

        while (num < 1 || num > 12) {
            System.out.print("Out of range. Enter number between 1 and 12 : ");
            num = scnr.nextInt();
        }
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        System.out.println("Factorial of " + num + " is " + result + ".");
        
    }

    public static void factorials() {
        Scanner scnr = new Scanner(System.in);

        int num;

        System.out.print("Enter Number : ");
        num = scnr.nextInt();

        while (num < 1 || num > 12) {
            System.out.print("Out of range. Enter number between 1 and 12 : ");
            num = scnr.nextInt();
        }
        
        System.out.println("Factorial for numbers between 1 and " + num + " are:");
        
        for (int i = 1; i <= num; i++) {
            // System.out.println("This is i" + i);
            // System.out.println("This is num" + num);
            int result = 1;
            for (int j = 1; j <= i; j++) {
                result *= j;
            }
            System.out.print(result);

            if (i != num) {
                System.out.print(", ");
            }

        }

        System.out.println();

    } 

    public static void userSum() {
        Scanner scnr = new Scanner(System.in);

        int num;
        double sum = 0.0;
        int count = 0;

        System.out.print("Enter Number : ");
        num = scnr.nextInt();

        while (num != -999) {
            sum += num;
            count++;

            System.out.print("Enter Number : ");
            num = scnr.nextInt();
        }

        System.out.printf("The sum of values is %.2f%n", sum);

        if (count == 0) {

            System.out.println("Average is : undefined");
        } else {

            double average = sum / count;
            System.out.printf("Average is : %.2f%n", average);
        }
    }

    public static void userMax() {
        Scanner scnr = new Scanner(System.in);

        int num;
        int max = 0;
        boolean hasValue = false;

        System.out.print("Enter Number : ");
        num = scnr.nextInt();

        while (num != -999) {

            if (!hasValue) {
                max = num;
                hasValue = true;
            } 
            else if (num > max) {
                max = num;
            }

            System.out.print("Enter Number : ");
            num = scnr.nextInt();
        }

        if (!hasValue) {
            System.out.println("The max of values is undefined.");
        } else {
            System.out.println("The max of values is " + max + ".");
        }
    }




}
