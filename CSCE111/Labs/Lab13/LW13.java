// array lists
//
import java.util.ArrayList;

public class LW13 {

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(3);
        list1.add(5);

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(7);
        list2.add(8);

        ArrayList<Integer> list3 = new ArrayList<Integer>();
        list3.add(6);
        list3.add(7);
        list3.add(8);

        // System.out.println(interleave(list1, list3)); // returns [1, 6, 3, 7, 5, 8]
        // System.out.println(interleave(list1, list2)); // returns [1, 2, 3, 4, 5, 6, 7, 8]
        // System.out.println(interleave(list2, list1)); // returns [2, 1, 4, 3, 6, 5, 7, 8]

        // Merge problem
        // System.out.println(merge(list1, list3));
        // System.out.println(merge(list1, list2));
        // System.out.println(merge(list2, list1));
        // System.out.println(merge(list2, list3));
    }

    // ArrayList<String> cars = new ArrayList<String>();
    // cars.add("Honda");
    // cars.get(0);
    // cars.set(0, "Japan");
    // cars.remove(0);
    // cars.clear();
    // cars.size();
    //

    public static ArrayList<Integer> interleave(
        ArrayList<Integer> list1,
        ArrayList<Integer> list2
    ) {
        int interleave_size = Math.min(list1.size(), list2.size());

        ArrayList<Integer> interleaved = new ArrayList<Integer>();
        for (int i = 0; i < interleave_size; i++) {
            interleaved.add(list1.get(i));
            interleaved.add(list2.get(i));
        }

        // int remaining = Math.max(list1.size(), list2.size()) - interleave_size;

        if (list1.size() > list2.size()) {
            for (int i = interleave_size; i < list1.size(); i++) {
                interleaved.add(list1.get(i));
            }
        } else if (list2.size() > list1.size()) {
            for (int i = interleave_size; i < list2.size(); i++) {
                interleaved.add(list2.get(i));
            }
        }

        return interleaved;
    }

    public static ArrayList<Integer> merge(
        ArrayList<Integer> list1,
        ArrayList<Integer> list2
    ) {
        int list1_counter = 0;
        int list2_counter = 0;

        ArrayList<Integer> merged = new ArrayList<Integer>();
        int lists_length = list1.size() + list2.size();

        for (int i = 0; i < lists_length; i++) {
            //
            //
            // This is if the end of one of the arrays is reached
            if (list1_counter == list1.size()) {
                for (int j = list2_counter; j < list2.size(); j++) {
                    merged.add(list2.get(j));
                }
                break;
            }
            if (list2_counter == list2.size()) {
                for (int j = list1_counter; j < list1.size(); j++) {
                    merged.add(list1.get(j));
                }
                break;
            }

            if (list1.get(list1_counter) > list2.get(list2_counter)) {
                merged.add(list2.get(list2_counter));
                list2_counter++;
            } else {
                merged.add(list1.get(list1_counter));
                list1_counter++;
            }
        }

        return merged;
    }
}
