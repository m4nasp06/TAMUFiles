import java.util.Scanner;


public class pythag {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Side1?");
        double side1 = scnr.nextDouble();
        System.out.println("Side2?");
        double side2 = scnr.nextDouble();

        double hypotenuse = Math.sqrt( side1 * side1 + side2 * side2);
        System.out.println(hypotenuse);
        scnr.close();
    }
}