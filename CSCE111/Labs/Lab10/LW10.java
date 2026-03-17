import java.util.Scanner;

public class LW10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // reverseTraversal(scanner);

        // int a[] = { 1, 2, 3, 4, 5 };
        // int b[] = { 5, 4, 3, 2, 1 };
        // System.out.println(isSortedAsc(a));
        // System.out.println(isSortedAsc(b));
        //
        // int a[] = { 1, 2, 3, 4, 5 };
        // int b[] = { 5, 4, 3, 2, 1 };
        // System.out.println(isSortedDesc(a));
        // System.out.println(isSortedDesc(b));
        //
        // int a[] = { 1, 2, 3, 4, 5 };
        // int b[] = { 5, 4, 3, 2, 1 };
        // int c[] = { 5, 4, 100, 2, 1 };

        // System.out.println("isSorted(a): " + isSorted(a)); // expected: true
        // System.out.println("isSorted(b): " + isSorted(b)); // expected: true
        // System.out.println("isSorted(c): " + isSorted(c)); // expected: false
        scanner.close();
    }

    public static void reverseTraversal(Scanner scnr) {
        System.out.print("Enter array size : ");
        int size = scnr.nextInt();
        int[] array = new int[size];
        for (int i = 1; i <= size; i++) {
            System.out.print("Enter integer " + i + " : ");
            array[i - 1] = scnr.nextInt();
        }

        System.out.print("Array in reverse is : ");
        for (int i = array.length - 1; i >= 0; i--) {
            if (i != 0) {
                System.out.print(array[i] + ", ");
            } else {
                System.out.println(array[i]);
            }
        }
    }

    public static boolean isSortedAsc(int a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            // System.out.println(a[i]);
            // System.out.println(a[i + 1]);
            if (!(a[i] < a[i + 1])) {
                return false;
            }
        }
        return true;
    }

    int a[] = { 1, 2, 3, 4, 5 };
    int b[] = { 5, 4, 3, 2, 1 };

    public static boolean isSortedDesc(int a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!(a[i] > a[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(int a[]) {
        if (isSortedAsc(a) || isSortedDesc(a)) {
            return true;
        }
        return false;
    }
}
