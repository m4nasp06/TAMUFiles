import java.util.Scanner;


public class LW05 {
    public static void main(String[] args) {
        // System.out.println(standardizePhoneNumber("123-456-7890"));
        // System.out.println(standardizePhoneNumber("(123)-4567890"));
        // System.out.println(standardizePhoneNumber(" (123 )- 456 7890 "));
        
        // printReceiptLine("Milk", 2, 2.55);
        // printReceiptLine("Bread", 1, 1.89);

    }

    public static String dateConversionToISO (String american) {
        String dat = american;
        int location = dat.indexOf("-");
        String month = dat.substring(0, location);
        String day = dat.substring(location,location);
        String year = dat.substring(location+1);
        int secondloc = year.indexOf("-");
        day = year.substring(0, secondloc);
        year = year.substring(secondloc+1);
        String amDate = year+"-"+month+"-"+day;
        return amDate;
    }
    public static String domainName(String email) {
        String ema = email;
        int location = ema.indexOf("@");
        String domName = ema.substring(location+1);
        domName = domName.toLowerCase();
        domName = domName.trim();
        return domName;

    }

    public static String standardizePhoneNumber(String number) {
        String change1 = number.replace(")", "");
        String change2 = change1.replace("-" , "");
        String change3 = change2.replace(" " , "");
        String change4 = change3.replace("(" , "");

        return change4;
    }

    public static void printReceiptLine(String item, int quantity, double price) {

        double total = quantity * price;
        System.out.printf("%-15s%10d%10.2f%10.2f%n", item, quantity, price, total);


    }

    
}