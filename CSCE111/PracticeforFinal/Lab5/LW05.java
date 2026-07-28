import java.util.Scanner;

public class LW05 {

	public static String dateConversionToISO(String american) {
		int firstDash = american.indexOf("-");
		String month = american.substring(0,firstDash);
		String day = american.substring(firstDash + 1, firstDash+3);
		int secondDash = american.indexOf("-" , firstDash+1);
		String year = american.substring(secondDash+1, secondDash+5);


		// System.out.println(year+"-"+month+"-"+day);
		return year+"-"+month+"-"+day;
	}

	public static String domainName(String email) {
		email = email.trim();
		email = email.toLowerCase();
		int at = email.indexOf("@");
		String domain = email.substring(at + 1);
		// System.out.println(domain);
		return domain;
	}

	public static String standardizePhoneNumber(String number) {
		number = number.replace("(","");
		number = number.replace(")","");
		number = number.replace("-","");
		number = number.replace(" ","");
		// System.out.println(number);
		return number;
	}

	public static void printReceiptLine(String item, int quantity, double price) {
		double total = quantity * price;
		// System.out.printf("%-15s",item,"%10d",quantity,"%10.2f",price,"%10.2f",total);
		System.out.printf("%-15s%10d%10.2f%10.2f\n",item,quantity,price,total);
		
	}

	public static void main(String[] args) {
		dateConversionToISO("05-12-2023");
		domainName("abc@tamu.edu");
		domainName("DEF@TAMU.EDU");
		domainName("  GHIJ@TAMU.edu   "); 
		standardizePhoneNumber("123-456-7890"); 
		standardizePhoneNumber("(123)-4567890"); 
		standardizePhoneNumber(" (123 )- 456 7890 "); 
		printReceiptLine("Milk", 2, 2.55);
		printReceiptLine("Bread", 1, 1.89);



	}



}