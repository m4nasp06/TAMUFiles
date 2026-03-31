// Manas Paramathmuni
// CSCE 111
// 635002312
// Section 503

import java.util.Scanner;

public class CalcDriver {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to the String Calculator.");
        System.out.print("Enter Your First Number: ");
        String num1 = scnr.nextLine();
        for (int i = 0; i < num1.length(); i++) {
            if (!Character.isDigit(num1.charAt(i))) {
                System.out.println("Invalid Input");
                return;
            }
        }
        System.out.print("Enter Your Second Number: ");
        String num2 = scnr.nextLine();

        // input verification, can only be digits
        for (int i = 0; i < num2.length(); i++) {
            if (!Character.isDigit(num2.charAt(i))) {
                System.out.println("Invalid Input");
                return;
            }
        }

        System.out.print("Enter Operation (+ or *): ");
        String operation = scnr.nextLine();

        if (operation.equals("+")) {
            System.out.println(
                num1 + " + " + num2 + " = " + Calc.add(num1, num2)
            );
        } else if (operation.equals("*")) {
            System.out.println(
                num1 + " * " + num2 + " = " + Calc.multiply(num1, num2)
            );
        } else {
            System.out.println("Invalid Input");
        }
    }
}
