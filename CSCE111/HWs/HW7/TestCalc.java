// Manas Paramathmuni
// CSCE 111
// 635002312
// Section 503
//
public class TestCalc {

    public static void main(String[] args) {
        String addResult = Calc.add("152", "273");
        System.out.println("Add Test:");
        System.out.println("152 + 273 = " + addResult); // expected: 425

        String multiplyResult = Calc.multiply("12", "12");
        System.out.println("\nMultiply Test:");
        System.out.println("12 * 12 = " + multiplyResult); // expected: 144

        System.out.println("\nExtra Tests:");

        System.out.println("999 + 1 = " + Calc.add("999", "1")); // 1000
        System.out.println("111111 * 2 = " + Calc.multiply("111111", "2")); // 222222
        System.out.println("0 * 12345 = " + Calc.multiply("0", "12345")); // 0
    }
}
