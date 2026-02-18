// Q2: Minimum of 3 Numbers
// Write a function int min3(int x, int y, int z), whose input is three integers, and the output is the smallest of the three values.
//  Remember that the minimum number is smaller than both of the other variables. You can combine multiple conditions using && operation. 
// You are not allowed to use Math.min or any function calls for this question.
// Examples
// min3(9, 5, 3);     returns 3
// min3(9, 6, 30);    returns 6
// min3(2, 6, 30);    returns 2


import java.util.Scanner;

public class LW06 {

    public static void main(String[] args) {
        // System.out.println(min3(9, 5, 3));
        // System.out.println(min3(9, 6, 30));
        // System.out.println(min3(2, 6, 30));
        // prefixCalculator();

    }

    public static int collatzStep(int x) {
        int num1 = x;
        if ((num1%2)==0) {
            int finalnum = num1 / 2;
            return finalnum;
        }
        else {
            int finalnum = (num1*3) + 1;
            return finalnum;
        }
        


    }
    public static int min3(int x, int y, int z) {
        
        if ( x < y && x < z) {
            return x;
        }
        else if (y < x && y < z) {
            return y;
        }
        else {
            return z;
        }
    }

    public static void prefixCalculator() {
        Scanner scnr  = new Scanner(System.in);
        System.out.print("Enter Expression : ");
        String operation = scnr.next();
        int num1 = 0, num2 = 0;

        if (operation.equals("abs")) {
            num1 = scnr.nextInt();
            if (num1 < 0) {
                System.out.println("abs " + num1 + " = " + (num1 * -1) );
            }
            else { 
                System.out.println("abs " + num1 + " = " + (num1) );

            }
        }
        else {
            num1 = scnr.nextInt();
            num2 = scnr.nextInt();
            if (operation.equals("+")) {
                System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
            }
            if (operation.equals("-")) {
                System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
            }
            if (operation.equals("*")) {
                System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
            }
            if (operation.equals("/")) {
                if (num2 == 0) {
                    System.out.print("Division by 0");
                }
                else {
                    System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
                }
            }

        }
        scnr.close();


    }
}