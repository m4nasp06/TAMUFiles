import java.util.Scanner;

public class LW11 {

    public static void main(String[] args) {
        wordFrequency();
    }

    public static void wordFrequency() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter a string : ");
        String line = scnr.nextLine();
        String[] splitLine = line.split(" ");
        System.out.println("Word frequency is: ");
        String[] words = new String[splitLine.length];
        int[] frequency = new int[splitLine.length];

        int idx = 0;

        for (int i = 0; i < splitLine.length; i++) {
            boolean wordFound = false;

            for (int j = 0; j < words.length; j++) {
                if (splitLine[i].equals(words[j])) {
                    frequency[j]++;
                    wordFound = true;
                    break;
                }
            }

            if (!wordFound) {
                words[idx] = splitLine[i];
                frequency[idx]++;
                idx++;
            }
        }

        for (int i = 0; i < splitLine.length; i++) {
            if (words[i] != null) {
                System.out.println(words[i] + " - " + frequency[i]);
            }
        }

        scnr.close();
    }
}
