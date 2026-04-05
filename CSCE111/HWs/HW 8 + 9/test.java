import java.util.ArrayList;

public class test {

    static int passed = 0;
    static int failed = 0;

    static void check(String name, boolean result) {
        if (result) { System.out.println("PASS: " + name); passed++; }
        else         { System.out.println("FAIL: " + name); failed++; }
    }

    static int evalHand(int[] holeRanks, String[] holeSuits, int[] commRanks, String[] commSuits) {
        Hand hand = new Hand();
        hand.addCard(new Card(holeRanks[0], holeSuits[0]));
        hand.addCard(new Card(holeRanks[1], holeSuits[1]));
        ArrayList<Card> community = new ArrayList<Card>();
        for (int i = 0; i < commRanks.length; i++) community.add(new Card(commRanks[i], commSuits[i]));
        HandEvaluator evaluator = new HandEvaluator();
        return evaluator.evaluate(hand, community);
    }

    public static void main(String[] args) {

        // === Card ===
        System.out.println("\n=== Card ===");
        check("Ace toString",   new Card(14,"S").toString().equals("A-S"));
        check("King toString",  new Card(13,"H").toString().equals("K-H"));
        check("Queen toString", new Card(12,"D").toString().equals("Q-D"));
        check("Jack toString",  new Card(11,"C").toString().equals("J-C"));
        check("10 toString",    new Card(10,"H").toString().equals("10-H"));
        check("2 toString",     new Card(2,"S").toString().equals("2-S"));
        check("getRank",        new Card(14,"S").getRank() == 14);
        check("getSuit",        new Card(14,"S").getSuit().equals("S"));

        // === Deck ===
        System.out.println("\n=== Deck ===");
        Deck deck = new Deck();
        int count = 0;
        ArrayList<String> seen = new ArrayList<String>();
        boolean dupe = false;
        for (int i = 0; i < 52; i++) {
            Card c = deck.dealCard(); count++;
            if (seen.contains(c.toString())) dupe = true;
            seen.add(c.toString());
        }
        check("52 cards",      count == 52);
        check("No duplicates", !dupe);
        Deck deck2 = new Deck();
        deck2.shuffle();
        ArrayList<String> s2 = new ArrayList<String>();
        boolean sd = false;
        for (int i = 0; i < 52; i++) {
            String k = deck2.dealCard().toString();
            if (s2.contains(k)) sd = true;
            s2.add(k);
        }
        check("Shuffle preserves 52 unique", !sd && s2.size() == 52);

        // === Hand ===
        System.out.println("\n=== Hand ===");
        Hand hand = new Hand();
        check("Starts empty",        hand.getHoleCards().size() == 0);
        check("Default handValue 0", hand.getHandValue() == 0);
        hand.addCard(new Card(14,"S")); hand.addCard(new Card(13,"H"));
        check("Holds 2 cards",       hand.getHoleCards().size() == 2);
        ArrayList<Card> best = new ArrayList<Card>();
        best.add(new Card(10,"S"));
        hand.setHandValue(5, best);
        check("setHandValue rank",   hand.getHandValue() == 5);
        check("setHandValue best",   hand.getYourHand().size() == 1);
        hand.clearHand();
        check("clearHand empty",     hand.getHoleCards().size() == 0);
        check("clearHand resets value", hand.getHandValue() == 0);

        // === Pot ===
        System.out.println("\n=== Pot ===");
        Pot pot = new Pot();
        check("Starts at 0",         pot.getPotSize() == 0);
        check("currentBet starts 0", pot.getCurrentBet() == 0);
        pot.addChips(50); pot.addChips(50);
        check("Accumulates chips",   pot.getPotSize() == 100);
        pot.setCurrentBet(20);
        check("setCurrentBet",       pot.getCurrentBet() == 20);
        pot.reset();
        check("reset clears total",  pot.getPotSize() == 0);
        check("reset clears bet",    pot.getCurrentBet() == 0);

        // === Player ===
        System.out.println("\n=== Player ===");
        Player p = new Player("Alice", 500);
        check("Starting chips",      p.getChips() == 500);
        check("Not folded",          !p.isFolded());
        check("Not all-in",          !p.isAllIn());
        int bet = p.placeBet(100);
        check("placeBet returns amt", bet == 100);
        check("placeBet deducts",    p.getChips() == 400);
        check("currentBet updated",  p.getCurrentBet() == 100);
        p.fold();
        check("fold works",          p.isFolded());
        Player p2 = new Player("Bob", 100);
        p2.placeBet(100);
        check("All-in at 0 chips",   p2.isAllIn());
        p.resetNewRound();
        check("resetNewRound fold",  !p.isFolded());
        check("resetNewRound allIn", !p.isAllIn());
        check("resetNewRound bet",   p.getCurrentBet() == 0);
        p.receiveChips(200);
        check("receiveChips",        p.getChips() == 600);
        p.placeBet(50);
        p.resetBet();
        check("resetBet clears",     p.getCurrentBet() == 0);

        // === HandEvaluator ===
        System.out.println("\n=== HandEvaluator ===");
        check("Royal Flush = 9",    evalHand(new int[]{14,13}, new String[]{"S","S"}, new int[]{12,11,10,2,3},   new String[]{"S","S","S","H","D"}) == 9);
        check("Straight Flush = 8", evalHand(new int[]{9,8},   new String[]{"H","H"}, new int[]{7,6,5,2,14},    new String[]{"H","H","H","D","S"}) == 8);
        check("Ace-low SF = 8",     evalHand(new int[]{14,2},  new String[]{"C","C"}, new int[]{3,4,5,9,10},    new String[]{"C","C","C","H","D"}) == 8);
        check("Four of a Kind = 7", evalHand(new int[]{7,7},   new String[]{"H","D"}, new int[]{7,7,2,3,9},     new String[]{"S","C","H","D","S"}) == 7);
        check("Full House = 6",     evalHand(new int[]{10,10}, new String[]{"H","D"}, new int[]{10,6,6,2,3},    new String[]{"S","H","D","H","D"}) == 6);
        check("Flush = 5",          evalHand(new int[]{2,5},   new String[]{"H","H"}, new int[]{9,11,13,3,7},   new String[]{"H","H","H","S","D"}) == 5);
        check("Straight = 4",       evalHand(new int[]{5,6},   new String[]{"H","D"}, new int[]{7,8,9,2,14},    new String[]{"S","C","H","S","D"}) == 4);
        check("Ace-low Straight=4", evalHand(new int[]{14,2},  new String[]{"H","D"}, new int[]{3,4,5,9,11},    new String[]{"S","C","H","D","S"}) == 4);
        check("Three of a Kind = 3",evalHand(new int[]{8,8},   new String[]{"H","D"}, new int[]{8,2,5,9,11},    new String[]{"S","H","D","C","H"}) == 3);
        check("Two Pair = 2",       evalHand(new int[]{4,4},   new String[]{"H","D"}, new int[]{9,9,2,5,11},    new String[]{"S","C","H","D","S"}) == 2);
        check("One Pair = 1",       evalHand(new int[]{3,3},   new String[]{"H","D"}, new int[]{7,9,11,2,5},    new String[]{"S","C","H","D","S"}) == 1);
        check("High Card = 0",      evalHand(new int[]{2,5},   new String[]{"H","D"}, new int[]{7,9,11,4,6},    new String[]{"S","C","H","D","S"}) == 0);
        check("Best from 7",        evalHand(new int[]{2,2},   new String[]{"H","D"}, new int[]{5,6,7,8,9},     new String[]{"S","S","S","S","S"}) == 8);

        // evaluate sets Hand fields
        Hand th = new Hand();
        th.addCard(new Card(14,"S")); th.addCard(new Card(13,"S"));
        ArrayList<Card> comm = new ArrayList<Card>();
        comm.add(new Card(12,"S")); comm.add(new Card(11,"S")); comm.add(new Card(10,"S"));
        comm.add(new Card(2,"H")); comm.add(new Card(3,"D"));
        new HandEvaluator().evaluate(th, comm);
        check("evaluate sets handValue", th.getHandValue() == 9);
        check("evaluate sets bestHand",  th.getYourHand().size() == 5);

        // === Summary ===
        System.out.println("\n=== Results: " + passed + " passed, " + failed + " failed ===");
    }
}
