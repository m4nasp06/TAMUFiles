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
                if (
                    value > highestValue ||
                    (value == highestValue &&
                        compareHands(five, bestHand, value) > 0)
                ) {
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
        for (int i = 0; i < cards.size(); i++) ranks[i] = cards
            .get(i)
            .getRank();
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

    // Builds a comparison key based on hand rank so that the most important
    // ranks come first (e.g. trips rank before kickers for Three of a Kind).
    private int[] getCompareKey(ArrayList<Card> hand, int handRank) {
        int[] freq = new int[15];
        for (Card c : hand) freq[c.getRank()]++;

        switch (handRank) {
            case 9: // Royal Flush — always a tie
                return new int[] { 0 };
            case 8: // Straight Flush
            case 4: // Straight
                // A-2-3-4-5 wheel: Ace plays as low, compare as 5-high
                if (
                    freq[14] > 0 &&
                    freq[2] > 0 &&
                    freq[3] > 0 &&
                    freq[4] > 0 &&
                    freq[5] > 0
                ) {
                    return new int[] { 5 };
                }
                for (int r = 14; r >= 2; r--) {
                    if (freq[r] > 0) return new int[] { r };
                }
            case 7: {
                // Four of a Kind: [quads rank, kicker]
                int quadsRank = -1,
                    kicker = -1;
                for (int r = 14; r >= 2; r--) {
                    if (freq[r] == 4) quadsRank = r;
                    else if (freq[r] == 1 && kicker == -1) kicker = r;
                }
                return new int[] { quadsRank, kicker };
            }
            case 6: {
                // Full House: [trips rank, pair rank]
                int tripsRank = -1,
                    pairRank = -1;
                for (int r = 14; r >= 2; r--) {
                    if (freq[r] == 3) tripsRank = r;
                    else if (freq[r] == 2) pairRank = r;
                }
                return new int[] { tripsRank, pairRank };
            }
            case 5: // Flush: compare all ranks high-to-low
            case 0: // High Card: compare all ranks high-to-low
                return getSortedRanks(hand);
            case 3: {
                // Three of a Kind: [trips rank, kicker1, kicker2]
                int tripsRank = -1;
                int[] kickers = new int[2];
                int ki = 0;
                for (int r = 14; r >= 2; r--) {
                    if (freq[r] == 3) tripsRank = r;
                    else if (freq[r] == 1 && ki < 2) kickers[ki++] = r;
                }
                return new int[] { tripsRank, kickers[0], kickers[1] };
            }
            case 2: {
                // Two Pair: [high pair rank, low pair rank, kicker]
                int highPair = -1,
                    lowPair = -1,
                    kicker = -1;
                for (int r = 14; r >= 2; r--) {
                    if (freq[r] == 2) {
                        if (highPair == -1) highPair = r;
                        else lowPair = r;
                    } else if (freq[r] == 1 && kicker == -1) {
                        kicker = r;
                    }
                }
                return new int[] { highPair, lowPair, kicker };
            }
            case 1: {
                // One Pair: [pair rank, kicker1, kicker2, kicker3]
                int pairRank = -1;
                int[] kickers = new int[3];
                int ki = 0;
                for (int r = 14; r >= 2; r--) {
                    if (freq[r] == 2) pairRank = r;
                    else if (freq[r] == 1 && ki < 3) kickers[ki++] = r;
                }
                return new int[] {
                    pairRank,
                    kickers[0],
                    kickers[1],
                    kickers[2],
                };
            }
            default:
                return getSortedRanks(hand);
        }
    }

    public int compareHands(
        ArrayList<Card> a,
        ArrayList<Card> b,
        int handRank
    ) {
        int[] ka = getCompareKey(a, handRank);
        int[] kb = getCompareKey(b, handRank);
        for (int i = 0; i < ka.length && i < kb.length; i++) {
            if (ka[i] != kb[i]) return ka[i] - kb[i];
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
