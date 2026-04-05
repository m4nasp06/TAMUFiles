import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> holeCards;
    private int handValue;
    private ArrayList<Card> yourHand;

    Hand() {
        holeCards = new ArrayList<Card>();
        handValue = 0;
        yourHand = new ArrayList<Card>();
    }

    public ArrayList<Card> getHoleCards() {
        return holeCards;
    }

    public int getHandValue() {
        return handValue;
    }

    public ArrayList<Card> getYourHand() {
        return yourHand;
    }

    public void setHandValue(int value, ArrayList<Card> best) {
        handValue = value;
        yourHand = best;
    }

    public void addCard(Card c) {
        holeCards.add(c);
    }

    public void clearHand() {
        holeCards.clear();
        handValue = 0;
        yourHand.clear();
    }
}
