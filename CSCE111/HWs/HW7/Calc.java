// Manas Paramathmuni
// CSCE 111
// 635002312
// Section 503
//

public class Calc {

    /**
     * Adds two large numbers represented as strings
     * Performs digit by digit addition, while manually checking for carry values
     *
     * @param a the first number in string form
     * @param b the second number in string form
     * @return the sum of a and b as a string
     */
    public static String add(String a, String b) {
        int i = a.length() - 1; // pointer for a 152
        int j = b.length() - 1; // pointer for b 273
        int carry = 0;

        String result = "";

        while (i >= 0 || j >= 0 || carry > 0) {
            int digA = 0;
            int digB = 0;

            // getting digits
            if (i >= 0) {
                digA = a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                digB = b.charAt(j) - '0';
                j--;
            }

            int sum = digA + digB + carry;
            int digit = sum % 10;
            carry = sum / 10;

            result = digit + result;
        }

        return result;
    }

    /**
     * Multiplies two numbers represented as Strings
     * Uses repeated addition of partial products, going digit by digit
     *
     * @param a the first number in string form
     * @param b the second number in string form
     * @return the product of a and b as a string
     */
    public static String multiply(String a, String b) {
        String total = "0";
        int zerosToAdd = 0;
        for (int i = b.length() - 1; i >= 0; i--) {
            int digitB = b.charAt(i) - '0';

            String partialProduct = multiplyByDigit(a, digitB);

            for (int j = 0; j < zerosToAdd; j++) {
                partialProduct = partialProduct + "0";
            }

            total = add(total, partialProduct);
            zerosToAdd++;
        }
        return total;
    }

    /**
     * Multiplies one large number by a single digit
     * Handles carry and builds the result
     *
     * @param a the number as a string
     * @param digit a single digit (0-9)
     * @return the product as a string
     */
    public static String multiplyByDigit(String a, int digit) {
        if (digit == 0) {
            return "0";
        }

        int carry = 0;
        String result = "";

        for (int i = a.length() - 1; i >= 0; i--) {
            int digA = a.charAt(i) - '0';
            int product = digA * digit + carry;

            int currentDigit = product % 10;
            carry = product / 10;

            result = currentDigit + result;
        }

        while (carry > 0) {
            int currentDigit = carry % 10;
            carry /= 10;
            result = currentDigit + result;
        }

        return result;
    }
}
