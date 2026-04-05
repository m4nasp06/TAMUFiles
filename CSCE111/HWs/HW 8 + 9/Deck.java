import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;
    private final String[] SUITS = { "H", "S", "C", "D" };

    Deck() {
        cards = new ArrayList<Card>();
        for (String suit : SUITS) {
            for (int rank = 2; rank <= 14; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Fisher-Yates
    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card dealCard() {
        return cards.remove(cards.size() - 1);
    }
}
