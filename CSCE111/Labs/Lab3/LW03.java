import java.util.Scanner;

public class LW03 {

    public static void main(String[] args) {

        System.out.println(approx_sin(Math.PI/2));
        System.out.println(triangleArea(3,4,5));
        triangle();

    }
    
    static double approx_sin(double x) {

        double pt1 = x;
        double pt2 = -(Math.pow(x,3) / 6);
        double pt3 = (Math.pow(x,5) / 120);
        double pt4 = -(Math.pow(x,7) / 5040);
        double sinx = pt1 + pt2 + pt3 + pt4;
        return sinx;
    }

    static double triangleArea(double a, double b, double c) {

        double s = (a + b + c) / 2;
        double area = Math.sqrt(s * (s-a) * (s-b) * (s-c));
        return area;
    }


    static void triangle() {

        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter Side 1: ");
        double a = scnr.nextDouble();
        // System.out.println();
        System.out.print("Enter Side 2: ");
        double b = scnr.nextDouble();
        // System.out.println();
        System.out.print("Enter Side 3: ");
        double c = scnr.nextDouble();
        
        double s = (a + b + c) / 2;
        double area = Math.sqrt(s * (s-a) * ( s-b) * (s-c));
        System.out.println("Area of Triangle is: "+area);
        scnr.close();
    }    

}