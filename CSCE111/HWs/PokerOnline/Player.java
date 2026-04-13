import java.io.*;

public class Player {

    private String name;
    private int chips;
    private Hand hand;
    private int currentBet;
    private boolean folded;
    private boolean allIn;
    private PrintWriter out;
    private BufferedReader in;
    private boolean isRemote;

    Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        this.hand = new Hand();
        this.currentBet = 0;
        this.folded = false;
        this.allIn = false;
    }

    public void setStreams(PrintWriter out, BufferedReader in, boolean isRemote) {
        this.out = out;
        this.in = in;
        this.isRemote = isRemote;
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public Hand getPlayerHand() {
        return hand;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean isFolded() {
        return folded;
    }

    public boolean isAllIn() {
        return allIn;
    }

    public int placeBet(int amount) {
        chips -= amount;
        currentBet += amount;
        if (chips <= 0) {
            allIn = true;
        }
        return amount;
    }

    public void fold() {
        folded = true;
    }

    public void receiveChips(int amount) {
        chips += amount;
    }

    public void resetNewRound() {
        currentBet = 0;
        folded = false;
        allIn = false;
        hand.clearHand();
    }

    public void resetBet() {
        currentBet = 0;
    }

    public void makeDecision(int potCurrentBet, Pot pot) {
        try {
            if (!isRemote) {
                out.println("--- Pass to " + name + " and press Enter ---");
                in.readLine();
            }
            out.println("Your hole cards:");
            out.println(Card.renderCards(hand.getHoleCards()));

            while (true) {
                out.println(name + ", your current chips: " + chips);
                out.println("Current bet to call: " + potCurrentBet);
                out.println("Pot size: " + pot.getPotSize());
                out.println("Enter your action (fold, call, check, raise): ");
                if (isRemote) out.println("INPUT_REQUIRED");
                String action = in.readLine();
                if (action == null) { fold(); return; }
                switch (action.toLowerCase().trim()) {
                    case "fold":
                        fold();
                        return;
                    case "check":
                        if (potCurrentBet > this.currentBet) {
                            out.println("You cannot check — there is a bet to call.");
                            break;
                        }
                        return;
                    case "call":
                        placeBet(Math.min(potCurrentBet - this.currentBet, chips));
                        return;
                    case "raise":
                        out.println("Enter total chips to put in (min " + (potCurrentBet - this.currentBet + 1) + ", max " + chips + "): ");
                        if (isRemote) out.println("INPUT_REQUIRED");
                        String raiseStr = in.readLine();
                        if (raiseStr == null) { fold(); return; }
                        try {
                            int raiseAmount = Integer.parseInt(raiseStr.trim());
                            if (raiseAmount < (potCurrentBet - this.currentBet + 1) || raiseAmount > chips) {
                                out.println("Invalid raise amount.");
                                break;
                            }
                            placeBet(raiseAmount);
                            return;
                        } catch (NumberFormatException e) {
                            out.println("Please enter a valid number.");
                            break;
                        }
                    default:
                        out.println("Invalid action. Please try again.");
                }
            }
        } catch (IOException e) {
            fold(); // treat disconnect as fold
        }
    }
}
