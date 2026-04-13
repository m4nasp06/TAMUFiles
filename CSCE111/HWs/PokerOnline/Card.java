import java.util.ArrayList;

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

    public String[] toLines() {
        String rankAsString = toString().split("-")[0];
        String type = suit;
        String[] lines = new String[7];
        lines[0] = "┌───────┐";
        lines[1] = "|" + String.format("%-7s", rankAsString) + "|";
        lines[2] = "|       |";
        lines[3] = "|   " + type + "   |";
        lines[4] = "|       |";
        lines[5] = "|" + String.format("%7s", rankAsString) + "|";
        lines[6] = "└───────┘";
        return lines;
    }

    public static String renderCards(ArrayList<Card> cards) {
        if (cards.isEmpty()) return "[none]";
        String[][] allLines = new String[cards.size()][];
        for (int i = 0; i < cards.size(); i++) {
            allLines[i] = cards.get(i).toLines();
        }

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < allLines[0].length; row++) {
            for (int card = 0; card < cards.size(); card++) {
                sb.append(allLines[card][row] + "  ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
