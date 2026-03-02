public class LW07 {

    public static void main(String[] args) {
        // System.out.println(leapYear(1712));
        // System.out.println(leapYear(1913));
        // System.out.println(leapYear(1800));
        // System.out.println(leapYear(2000));
        // System.out.println(shippingCostCalculation("International", 2, 300)); // expected: 0
        // System.out.println(shippingCostCalculation("International", 200, 300)); // expected: 30
        // System.out.println(shippingCostCalculation("domestic", 200, 300)); // expected: 0
        // System.out.println(shippingCostCalculation("domestic", 200, 50)); // expected: 15
        // System.out.println(shippingCostCalculation("domestic", 75, 50)); // expected: 10
        // System.out.println(shippingCostCalculation("domestic", 35, 50)); // expected: 5
        // System.out.println(shippingCostCalculation("domestic", 5, 50)); // expected: 2
        // System.out.println(shippingCostCalculation("domestic", 2, 50)); // expected: 2
        // System.out.println(shippingCostCalculation("domestic", 0.5, 50)); // expected: 0
        // System.out.println(
        //     flagPhishing(
        //         "hello.gmail.com",
        //         "Account Information",
        //         "Change your password at http://pmcbank.com"
        //     )
        // ); // expected: true
        // System.out.println(
        //     flagPhishing(
        //         "admin@gmai1.com",
        //         "Update Required",
        //         "Please verify your CREDENTIALS."
        //     )
        // ); // expected: true
        // System.out.println(
        //     flagPhishing(
        //         "support@gmail.com",
        //         "URGENT: Action Required",
        //         "Click here: http://login-secure.com"
        //     )
        // ); // expected: true
        // System.out.println(
        //     flagPhishing(
        //         "friend@gmail.com",
        //         "You are a winner",
        //         "Visit https://prize.com"
        //     )
        // ); // expected: false
        // System.out.println(
        //     flagPhishing(
        //         "newsletter@shop.com",
        //         "Weekly Deals",
        //         "Check out our new items."
        //     )
        // ); // expected: false

    }

    public static boolean leapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else {
            if (year % 100 != 0) {
                return true;
            } else if (year % 400 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static double shippingCostCalculation(
        String type,
        double weight,
        double orderTotal
    ) {
        if (type.equals("International")) {
            if ((orderTotal > 100) & (weight < 5)) {
                return 0;
            } else {
                return 30;
            }
        } else {
            if (orderTotal > 100) {
                return 0;
            }
            if (weight > 100) {
                return 15;
            } else if (weight > 50) {
                return 10;
            } else if (weight > 10) {
                return 5;
            } else if (weight > 1) {
                return 2;
            } else {
                return 0;
            }
        }
    }

    public static boolean flagPhishing(
        String sender,
        String subject,
        String content
    ) {
        sender.toLowerCase();
        boolean condition1 = false;
        boolean condition2 = false;
        boolean condition3 = false;
        boolean condition4 = false;
        if (!sender.contains("gmail.com")) {
            condition1 = true;
        }
        if (
            subject.contains("URGENT") ||
            subject.contains("Account Suspended") ||
            subject.contains("Winner")
        ) {
            condition2 = true;
        }
        if (content.contains("http://")) {
            condition3 = true;
        }
        String contentLowered = content.toLowerCase();
        if (
            contentLowered.contains("password") ||
            contentLowered.contains("credentials")
        ) {
            condition4 = true;
        }

        int x = condition1 ? 1 : 0;
        int y = condition2 ? 1 : 0;
        int z = condition3 ? 1 : 0;
        int u = condition4 ? 1 : 0;
        int sum = x + y + z + u;
        if (sum >= 2) {
            return true;
        }
        return false;
    }
}
