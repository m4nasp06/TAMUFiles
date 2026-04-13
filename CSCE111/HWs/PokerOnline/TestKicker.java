import java.util.ArrayList;

public class TestKicker {
    static HandEvaluator eval = new HandEvaluator();

    public static void main(String[] args) {
        System.out.println("=== Kicker / High Card Tiebreak Tests ===\n");

        // Test 1: Both high card, Q-high vs J-high
        test("High Card: Q-high vs J-high",
            new int[]{12, 7}, new String[]{"S", "C"},   // Q, 7
            new int[]{11, 9}, new String[]{"H", "S"},    // J, 9
            new int[]{3, 3, 14, 2, 2}, new String[]{"C", "D", "H", "D", "H"},  // board: 3,3,A,2,2
            "Player 1 should win (Q > J as kicker with same two pair)");

        // Test 2: Both pure high card, no pairs on board
        test("Pure High Card: K-high vs 10-high",
            new int[]{13, 4}, new String[]{"S", "C"},    // K, 4
            new int[]{10, 6}, new String[]{"H", "D"},    // 10, 6
            new int[]{8, 5, 3, 2, 7}, new String[]{"C", "D", "H", "S", "C"},
            "Player 1 should win (K > 10)");

        // Test 3: Both high card, same highest card, different second kicker
        test("High Card: A-Q vs A-J (same top card, different 2nd)",
            new int[]{14, 12}, new String[]{"S", "C"},   // A, Q
            new int[]{14, 11}, new String[]{"H", "D"},   // A, J
            new int[]{8, 5, 3, 2, 7}, new String[]{"C", "D", "H", "S", "C"},
            "Player 1 should win (Q kicker > J kicker)");

        // Test 4: Identical hands — true tie
        test("True tie: both have same best 5 cards",
            new int[]{4, 3}, new String[]{"S", "C"},     // 4, 3
            new int[]{4, 3}, new String[]{"H", "D"},     // 4, 3
            new int[]{14, 13, 12, 11, 10}, new String[]{"C", "D", "H", "S", "C"},
            "Should be a TRUE tie (board plays for both)");

        // Test 5: Both one pair, different kicker
        test("One Pair: both pair of 8s, K kicker vs 9 kicker",
            new int[]{13, 2}, new String[]{"S", "C"},    // K, 2
            new int[]{9, 3}, new String[]{"H", "D"},     // 9, 3
            new int[]{8, 8, 5, 4, 6}, new String[]{"C", "D", "H", "S", "C"},
            "Player 1 should win (K kicker > 9 kicker)");

        // Test 6: Two pair where kicker actually matters (no Ace on board)
        test("Two Pair: same pairs, Q kicker vs J kicker",
            new int[]{12, 4}, new String[]{"S", "C"},    // Q, 4
            new int[]{11, 4}, new String[]{"H", "D"},    // J, 4
            new int[]{3, 3, 9, 2, 2}, new String[]{"C", "D", "H", "D", "H"},
            "Player 1 should win (Q kicker > J kicker with same two pair)");

        // Test 7: Both high card, all 5 best cards identical from board
        test("High Card: low hole cards, board dominates",
            new int[]{2, 3}, new String[]{"S", "C"},     // 2, 3
            new int[]{2, 4}, new String[]{"H", "D"},     // 2, 4
            new int[]{14, 13, 12, 11, 9}, new String[]{"C", "D", "H", "S", "C"},
            "Should be a TRUE tie (board A,K,Q,J,9 plays for both)");
    }

    static void test(String name, int[] ranks1, String[] suits1,
                     int[] ranks2, String[] suits2,
                     int[] boardRanks, String[] boardSuits, String expected) {
        Hand h1 = new Hand();
        h1.addCard(new Card(ranks1[0], suits1[0]));
        h1.addCard(new Card(ranks1[1], suits1[1]));

        Hand h2 = new Hand();
        h2.addCard(new Card(ranks2[0], suits2[0]));
        h2.addCard(new Card(ranks2[1], suits2[1]));

        ArrayList<Card> community = new ArrayList<>();
        for (int i = 0; i < boardRanks.length; i++) {
            community.add(new Card(boardRanks[i], boardSuits[i]));
        }

        int val1 = eval.evaluate(h1, community);
        int val2 = eval.evaluate(h2, community);

        System.out.println("--- " + name + " ---");
        System.out.println("P1 hand value: " + val1 + " (" + HandEvaluator.HAND_NAMES[val1] + ")");
        System.out.println("P2 hand value: " + val2 + " (" + HandEvaluator.HAND_NAMES[val2] + ")");

        if (val1 != val2) {
            String winner = val1 > val2 ? "Player 1" : "Player 2";
            System.out.println("Winner by hand rank: " + winner);
        } else {
            int cmp = eval.compareHands(h1.getYourBestHand(), h2.getYourBestHand(), val1);
            if (cmp > 0) System.out.println("Winner by kicker: Player 1");
            else if (cmp < 0) System.out.println("Winner by kicker: Player 2");
            else System.out.println("Result: TRUE TIE");
        }
        System.out.println("Expected: " + expected);
        System.out.println();
    }
}
