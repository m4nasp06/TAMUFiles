// Manas Paramathmuni
// CSCE 111 Section 503
// 02/08/2026

import java.util.Scanner;


public class NameGame {

  private static final String MY_NAME = "Manas";

  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);

    // Intro
    System.out.println("The Name Game!");
    System.out.println();
    System.out.println();
    System.out.println("Come on everybody, I said now lets play a game.");
    System.out.println("I bet'cha I could make a rhyme");
    System.out.println("Out of anybody's name...");
    System.out.println();
    System.out.println();

    // Prompt
    System.out.println("What is your name?");
    String name = scnr.nextLine();
    System.out.println();

    //string manip
    String trimmed = name.trim();
    String lower = trimmed.toLowerCase();
    String upper = trimmed.toUpperCase();

    System.out.println("Howdy " + trimmed + "!");
    System.out.println();
    System.out.println();

    // Not allowed to play
    if (containsBannedSubstring(lower)) {
      System.out.println("Sorry " + upper + ", but you can't play this game.");
      System.out.print('\u0007'); // bell
      scnr.close();
      return;
    }

    

    // Embarrassing names (examples: Marty, Art)
    // 
    if (isEmbarrassingName(lower)) {
      System.out.println(
          "Howdy, this name can be embarrassing for some users, would you like to continue? Type yes or no");
      String answer = scnr.nextLine().trim();
      if (answer.equalsIgnoreCase("no")) {
        scnr.close();
        return;
      }
    }

    // name matches 
    if (trimmed.equalsIgnoreCase(MY_NAME)) {
      System.out.println("Hey, that's my name too!");
    }

    // Rhyme header
    System.out.println("Okay " + upper + ", here is your rhyme:");
    System.out.println();

    // Build the base 
    String base = buildBase(upper);

    // Apply B/F/M first-letter exceptions 
    boolean startsWithB = upper.startsWith("B");
    boolean startsWithF = upper.startsWith("F");
    boolean startsWithM = upper.startsWith("M");

    String line1Tail = startsWithB ? base : ("B" + base);
    String line2Tail = startsWithF ? base : ("F" + base);
    String line3Tail = startsWithM ? base : ("M" + base);

    // Print rhyme lines
    System.out.println(upper + " " + upper + " bo-" + line1Tail);
    System.out.println("Banana fana fo-" + line2Tail);
    System.out.println("fe fi mo-" + line3Tail);
    System.out.println(upper + "!");
    System.out.println();
    System.out.println();
    // Thank you line
    System.out.print("Thank you for playing, " + upper + "!");

    scnr.close();
  }


  private static boolean containsBannedSubstring(String lowerName) {
 
    return lowerName.contains("mitch")
        || lowerName.contains("tuck")
        || lowerName.contains("stitch")
        || lowerName.contains("uck");
  }

  private static boolean isEmbarrassingName(String lowerName) {
    return lowerName.contains("art") || lowerName.equals("marty");
  }

  private static String buildBase(String upperName) {

    // - single consonant: remove 1 (Sarah -> ARAH)
    // - single vowel: remove 0 (Anna -> ANNA)
    // - double consonant: remove 2 (AS)Thomas -> OM
    // - double vowel: remove 1 (Aaron -> ARON)

    String lower = upperName.toLowerCase();
    int len = lower.length();

    if (len == 0) return "";
    if (len == 1) {
      char c0 = lower.charAt(0);
      if (isVowel(c0)) return upperName; // single vowel: remove 0
      return ""; // single consonant: remove 1, but length is 1 -> empty base
    }

    char c0 = lower.charAt(0);
    char c1 = lower.charAt(1);

    boolean v0 = isVowel(c0);
    boolean v1 = isVowel(c1);

    int removeCount;
    if (v0 && v1) {
      removeCount = 1; // double vowel
    } else if (!v0 && !v1) {
      removeCount = 2; // double consonant
    } else if (v0) {
      removeCount = 0; // single vowel
    } else {
      removeCount = 1; // single consonant
    }

    if (removeCount >= len) return "";
    return upperName.substring(removeCount);
  }

  private static boolean isVowel(char ch) {
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
  }
}