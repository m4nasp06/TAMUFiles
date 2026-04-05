public class Card {

    private int rank;
    private String suit;
    private String rankLetter;
    private String[] faceCards = { "J", "Q", "K", "A" };

    Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public String toString() {
        if (rank < 11) {
            rankLetter = Integer.toString(rank);
            return rankLetter + "-" + suit;
        } else {
            return faceCards[rank - 11] + "-" + suit;
        }
    }
}
