public class Card {

    private String suit;
    private String rank;

    private static final String[] VALID_SUITS = {
        "Heart",
        "Spade",
        "Club",
        "Diamond",
    };
    private static final String[] VALID_RANKS = {
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "A",
        "J",
        "Q",
        "K",
    };

    public Card(String suit, String rank)
        throws InvalidSuitException, InvalidRankException {
        boolean validSuit = false;
        for (int i = 0; i < VALID_SUITS.length; i++) {
            if (VALID_SUITS[i].equals(suit)) {
                validSuit = true;
                break;
            }
        }
        if (!validSuit) {
            throw new InvalidSuitException(suit + " is not a valid suit");
        }

        boolean validRank = false;
        for (int i = 0; i < VALID_RANKS.length; i++) {
            if (VALID_RANKS[i].equals(rank)) {
                validRank = true;
                break;
            }
        }
        if (!validRank) {
            throw new InvalidRankException(rank + " is not a valid rank");
        }

        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Suit: " + suit + ", Rank: " + rank;
    }
}
