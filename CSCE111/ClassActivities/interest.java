import java.util.Scanner;

public class interest {

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Principal Amount?");
        double princip = scnr.nextDouble();
        System.out.println("A.I.R.?");
        double ann_int_rate = scnr.nextDouble();
        System.out.println("Months?");
        double months = scnr.nextDouble();
        
        double total = princip * Math.pow((1 + (ann_int_rate / 1200)) , months);
        System.out.println(total);
        scnr.close();
    
    }
}
