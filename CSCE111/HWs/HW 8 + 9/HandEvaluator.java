import java.util.ArrayList;

public class HandEvaluator {

    public static final String[] HAND_NAMES = {
        "High Card",
        "One Pair",
        "Two Pair",
        "Three of a Kind",
        "Straight",
        "Flush",
        "Full House",
        "Four of a Kind",
        "Straight Flush",
        "Royal Flush",
    };

    public int evaluate(Hand hand, ArrayList<Card> community) {
        ArrayList<Card> allCards = new ArrayList<Card>();
        allCards.addAll(hand.getHoleCards());
        allCards.addAll(community);
        ArrayList<Card> bestHand = new ArrayList<Card>();
        int highestValue = -1;
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 7; j++) {
                ArrayList<Card> five = new ArrayList<Card>();
                for (int k = 0; k < 7; k++) {
                    if (k != i && k != j) {
                        five.add(allCards.get(k));
                    }
                }
                int value = handValue(five);
                if (value > highestValue || (value == highestValue && compareHands(five, bestHand) > 0)) {
                    highestValue = value;
                    bestHand = five;
                }
            }
        }
        hand.setHandValue(highestValue, bestHand);
        return highestValue;
    }

    private int handValue(ArrayList<Card> five) {
        int[] freq = new int[15];
        String firstSuit = five.get(0).getSuit();
        boolean flush = true;

        for (Card card : five) {
            freq[card.getRank()]++;
            if (!card.getSuit().equals(firstSuit)) {
                flush = false;
            }
        }

        int pairs = 0;
        int trips = 0;
        int quads = 0;
        for (int count : freq) {
            if (count == 2) pairs++;
            if (count == 3) trips++;
            if (count == 4) quads++;
        }

        boolean straight = isStraight(freq);
        if (straight && flush && freq[14] == 1 && freq[10] == 1) return 9;
        if (straight && flush) return 8;
        if (quads == 1) return 7;
        if (trips == 1 && pairs == 1) return 6;
        if (flush) return 5;
        if (straight) return 4;
        if (trips == 1) return 3;
        if (pairs == 2) return 2;
        if (pairs == 1) return 1;
        return 0;
    }

    private int[] getSortedRanks(ArrayList<Card> cards) {
        int[] ranks = new int[cards.size()];
        for (int i = 0; i < cards.size(); i++) ranks[i] = cards.get(i).getRank();
        for (int i = 1; i < ranks.length; i++) {
            int key = ranks[i];
            int j = i - 1;
            while (j >= 0 && ranks[j] < key) {
                ranks[j + 1] = ranks[j];
                j--;
            }
            ranks[j + 1] = key;
        }
        return ranks;
    }

    public int compareHands(ArrayList<Card> a, ArrayList<Card> b) {
        int[] ra = getSortedRanks(a);
        int[] rb = getSortedRanks(b);
        for (int i = 0; i < ra.length && i < rb.length; i++) {
            if (ra[i] != rb[i]) return ra[i] - rb[i];
        }
        return 0;
    }

    private boolean isStraight(int[] freq) {
        int consecutive = 0;
        int[] adjusted = freq.clone();
        adjusted[1] = adjusted[14]; //handling for 1,2,3,4,5 or
        for (int i = 1; i < adjusted.length; i++) {
            if (adjusted[i] > 0) {
                consecutive++;
                if (consecutive == 5) {
                    return true;
                }
            } else {
                consecutive = 0;
            }
        }

        return false;
    }
}
