import java.util.Scanner;


public class LW04 {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter number of seconds: ");
        int second = scnr.nextInt();
        timeConversion(second);
        scnr.close();
    }

    public static int nth_digit(int x, int n) {

        Double power = Math.pow(10, n);
        x /= power;
        return x % 10;

    }

    public static int reverse_digits(int x) {
        int digit1 = nth_digit(x, 0);
        // System.out.println("digit1" + digit1);
        int digit2 = nth_digit(x, 1);
        // System.out.println("digit2" + digit2);
        int digit3 = nth_digit(x, 2);
        // System.out.println("digit3" + digit3);
        int digit4 = nth_digit(x, 3);
        // System.out.println("digit4" + digit4);

        digit1 *= 1000;
        // System.out.println("digit1" + digit1);
        digit2 *= 100;
        // System.out.println("digit2" + digit2);
        digit3 *= 10;
        // System.out.println("digit3" + digit3);
        digit4 *= 1;
        // System.out.println("digit4" + digit4);

        return digit4 + digit3 + digit2 + digit1;
        

    }

    public static void reverse_digits() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter a number < 9999: ");
        int num = scnr.nextInt();
        int reversedNum = reverse_digits(num);
        System.out.print("Number with reversed digits is: " + reversedNum);
        scnr.close();

    }
    
    public static int fair_die() {
        int prob = (int) (Math.random() * 6.0) + 1;

        return prob;

    }

    public static void timeConversion(int seconds) {
        int sec = seconds;
        int hours = seconds / 3600;
        seconds -= hours* 3600;
        int minutes = seconds / 60;
        seconds -= 60*minutes;
        // int leftoverS = second % 60;
       
        // int leftoverM = minute % 60;
        // System.out.print(second + " seconds = " + hours + " hours, " + leftoverM + " minutes and " + leftoverS + " seconds");
        // System.out.println(minute);
        System.out.print( sec + " seconds = " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds");
    }
}
