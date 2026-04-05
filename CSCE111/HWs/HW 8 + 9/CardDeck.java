// Manas Paramathmuni
// 635002312
// Section 503

public class CardDeck {

    // Global variables of suits and vals for convenience, I can use them in any function as needed
    public static char[] suits = { 'H', 'S', 'C', 'D', 'j' };
    public static char[] vals = {
        'A',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
        '9',
        'T',
        'J',
        'Q',
        'K',
    };
    public static char[] jokerVals = { '0', '1' };

    // This didn't need to be a global function, but chose to
    public static String[] deckOfCards = new String[54];

    public static void main(String[] args) {
        // Three functions, self explanatory
        createDeck();

        shuffle(deckOfCards);

        dealCards(deckOfCards);
    }

    public static void createDeck() {
        // Using an index to assign 54 cards to 54 elements
        int i = 0;
        for (char suit : suits) {
            // for each suit
            if (suit == 'j') {
                // joker only gets 0,1
                for (char jokerVal : jokerVals) {
                    deckOfCards[i] = suit + "-" + jokerVal;
                    System.out.printf("%-6s", suit + "-" + jokerVal);
                    i++;
                }
            } else {
                for (char val : vals) {
                    // there are 13 possible values, the global variable allows me to just pick and combine
                    deckOfCards[i] = suit + "-" + val;
                    System.out.printf("%-6s", suit + "-" + val);
                    i++;
                }
            }

            System.out.println();
        }
    }

    public static void shuffle(String[] cardDeck) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            // Running this i many times
            int j = (int) (Math.random() * 51) + 1; // pick a random card 1-52 and swap it with the first card
            String cardZero = cardDeck[0]; // swap mechanism
            cardDeck[0] = cardDeck[j];
            cardDeck[j] = cardZero;
        }

        int i = 0;
        for (String card : cardDeck) {
            // print each card shuffled
            if (i % 13 == 0) {
                System.out.println();
            }
            System.out.printf("%-6s", card);
            i++;
        }

        System.out.println();
    }

    public static void dealCards(String[] cardDeck) {
        int idx = 0;
        // created 6 1-D array of strings
        String[] player1 = new String[10];
        String[] player2 = new String[10];
        String[] player3 = new String[10];
        String[] player4 = new String[10];
        String[] stock = new String[12];
        String[] discard = new String[2];

        // use switch case to apply logic to 6 arrays
        for (int i = 1; i <= 6; i++) {
            switch (i) {
                case 1:
                    for (int j = 0; j < 10; j++) {
                        player1[j] = cardDeck[idx + j];
                    }
                    idx += 10;
                    break;
                case 2:
                    for (int j = 0; j < 10; j++) {
                        player2[j] = cardDeck[idx + j];
                    }
                    idx += 10;
                    break;
                case 3:
                    for (int j = 0; j < 10; j++) {
                        player3[j] = cardDeck[idx + j];
                    }
                    idx += 10;
                    break;
                case 4:
                    for (int j = 0; j < 10; j++) {
                        player4[j] = cardDeck[idx + j];
                    }
                    idx += 10;
                    break;
                case 5:
                    for (int j = 0; j < 12; j++) {
                        stock[j] = cardDeck[idx + j];
                    }
                    idx += 12;
                    break;
                case 6:
                    for (int j = 0; j < 2; j++) {
                        discard[j] = cardDeck[idx + j];
                    }
                    break;
            }
        }

        System.out.print("Player 1's Hand:  ");
        for (String card : player1) System.out.printf("%-6s", card);
        System.out.println();

        System.out.print("Player 2's Hand:  ");
        for (String card : player2) System.out.printf("%-6s", card);
        System.out.println();

        System.out.print("Player 3's Hand:  ");
        for (String card : player3) System.out.printf("%-6s", card);
        System.out.println();

        System.out.print("Player 4's Hand:  ");
        for (String card : player4) System.out.printf("%-6s", card);
        System.out.println();

        System.out.print("Stock Pile:       ");
        for (String card : stock) System.out.printf("%-6s", card);
        System.out.println();

        System.out.print("Discard Pile:     ");
        for (String card : discard) System.out.printf("%-6s", card);
        System.out.println();
    }
}
