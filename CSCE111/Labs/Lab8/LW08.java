public class LW08 {

    public static void main(String[] args) {
        // homeworkPolicy();
        // System.out.println(factorial(5));
        // System.out.println(stringCleaner("-Hello, 1 world$!"));
        // System.out.println(stringCleaner(" -*&^$^&*NFH_)NJK^ $!"));
        // System.out.println(collatzSequence(6));
        // System.out.println(collatzSequence(27));
    }

    public static void homeworkPolicy() {
        for (int i = 0; i < 20; i++) {
            System.out.println(
                "Homework must be done without help from other students."
            );
            System.out.println(
                "Homework code should not be shared with any student."
            );
            System.out.println(
                "Sharing homework code will result in penalties under the course plagiarism policies."
            );
            System.out.println(
                "If I need help on homework, I will go to TA office hours or post on Campuswire."
            );
            System.out.println("I will not ask other students for help.");
            System.out.println();
        }
    }

    public static int factorial(int n) {
        for (int i = n - 1; i > 0; i--) {
            n *= i;
        }
        return n;
    }

    public static String stringCleaner(String str) {
        String cleaned = "";
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                cleaned += str.charAt(i);
            }
        }
        return cleaned;
    }

    public static int collatzSequence(int n) {
        int steps = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                n /= 2;
                steps++;
            } else {
                n = n * 3 + 1;
                steps++;
            }
        }
        return steps;
    }
}
