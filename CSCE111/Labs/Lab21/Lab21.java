import java.util.Scanner;

public class Lab21 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean success = false;

        while (!success) {
            System.out.print("Enter two numbers: ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            if (parts.length > 2) {
                System.out.println("You entered too many numbers. Try again.");
                continue;
            }
            if (parts.length < 2) {
                System.out.println("You entered too few numbers. Try again.");
                continue;
            }

            int first, second;

            try {
                first = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.println(
                    "The first value is not a number. Try again."
                );
                continue;
            }

            try {
                second = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println(
                    "The second value is not a number. Try again."
                );
                continue;
            }

            System.out.println(
                first + " + " + second + " = " + (first + second)
            );
            success = true;
        }

        scanner.close();
    }
}
