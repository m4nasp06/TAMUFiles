public class LW12 {

    public static void main(String[] args) {
        // int[] a = { 1, 2, -3, -4 };
        // int[] b = { 1, -3, -4, 2 };
        // int[] c = { 1, -3, -4, -2 };
        // System.out.println(findFirstEven(a)); // expected: 1
        // System.out.println(findFirstEven(b)); // expected: 3
        // System.out.println(findFirstEven(c)); // expected: -1
        //     int[] a = null;
        //     System.out.println(java.util.Arrays.toString(filterBy3(a))); // expected: null
        //     int[] b = { 2, 5, 8 };
        //     System.out.println(java.util.Arrays.toString(filterBy3(b))); // expected: null
        //     int[] c = { 1, 2, 5, 200, 3, 20, 21 };
        //     System.out.println(java.util.Arrays.toString(filterBy3(c))); // expected: null
        //     int[] d = { 1, 2, 10, 18, 200, 3, 20, 21 };
        //     System.out.println(java.util.Arrays.toString(filterBy3(d))); // expected: [18]
        //     int[] e = {
        //         2,
        //         5,
        //         6,
        //         10,
        //         18,
        //         25,
        //         30,
        //         44,
        //         54,
        //         61,
        //         75,
        //         90,
        //         99,
        //         120,
        //         150,
        //         180,
        //     };
        //     System.out.println(java.util.Arrays.toString(filterBy3(e))); // expected: [6, 18, 30, 54, 75, 90, 99]
        // int[] a = null;
        // // reverseInPlace(a);                                           // would throw NullPointerException
        // System.out.println(java.util.Arrays.toString(a)); // expected: null
        // int[] b = { 2, 5, 8 };
        // reverseInPlace(b);
        // System.out.println(java.util.Arrays.toString(b)); // expected: [8, 5, 2]
        // int[] c = { 1, 2, 5, 200, 3, 20, 21 };
        // reverseInPlace(c);
        // System.out.println(java.util.Arrays.toString(c)); // expected: [21, 20, 3, 200, 5, 2, 1]
    }

    // Use Map, Reduce and Filter Patterns with Arrays

    public static int findFirstEven(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0 && a[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int[] filterBy3(int[] a) {
        if (a == null) {
            return null;
        }

        int size_needed = 0;
        for (int item : a) {
            if (item > 100) break;
            if (item % 3 == 0) {
                size_needed++;
            }
        }

        if (size_needed == 0) return null;

        int[] new_array = new int[size_needed];

        int j = 0;
        for (int item : a) {
            if (item > 100) {
                break;
            }
            if (item % 3 == 0) {
                new_array[j] = item;
                j++;
            }
        }

        return new_array;
    }

    public static void reverseInPlace(int[] list) {
        if (list == null) {
            return;
        }
        int reverseIdx = list.length - 1;

        for (int i = 0; i < list.length / 2; i++) {
            int temp = list[i];
            list[i] = list[reverseIdx];
            list[reverseIdx] = temp;
            reverseIdx--;
        }
    }

    public static double trace(double[][] matrix) {
        int length = matrix.length;
        // m2 = {{1, 5, 0},
        //      {2, 4, 5.5},
        //      {8, 9, 7.6}}
        double trace = 0;
        // int reverse_idx = matrix.length;
        for (int i = 0; i < length; i++) {
            trace += matrix[i][i];
            // trace += matrix[reverse_idx][reverse_idx];
            // reverse_idx--;
        }

        return trace;
    }
}
