import java.util.Scanner;

public class Triangles {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int side1 = scnr.nextInt();
        int side2 = scnr.nextInt();
        int side3 = scnr.nextInt();
        int perimeter = side1 + side2 + side3;
        System.out.println("The perimeter is " + perimeter);

        side1 = scnr.nextInt();
        side2 = scnr.nextInt();
        side3 = scnr.nextInt();
        perimeter = side1 + side2 + side3;
        System.out.println("The perimeter is " + perimeter);

        side1 = scnr.nextInt();
        side2 = scnr.nextInt();
        side3 = scnr.nextInt();
        perimeter = side1 + side2 + side3;
        System.out.println("The perimeter is " + perimeter);
        scnr.close();

    }
}
