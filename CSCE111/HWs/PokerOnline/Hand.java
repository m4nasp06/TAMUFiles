import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> holeCards;
    private int handValue;
    private ArrayList<Card> yourBestHand;

    Hand() {
        holeCards = new ArrayList<Card>();
        handValue = 0;
        yourBestHand = new ArrayList<Card>();
    }

    public ArrayList<Card> getHoleCards() {
        return holeCards;
    }

    public int getHandValue() {
        return handValue;
    }

    public ArrayList<Card> getYourBestHand() {
        return yourBestHand;
    }

    public void setHandValue(int value, ArrayList<Card> best) {
        handValue = value;
        yourBestHand = best;
    }

    public void addCard(Card c) {
        holeCards.add(c);
    }

    public void clearHand() {
        holeCards.clear();
        handValue = 0;
        yourBestHand.clear();
    }
}
