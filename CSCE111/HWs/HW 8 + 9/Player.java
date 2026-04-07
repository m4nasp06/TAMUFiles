import java.util.Scanner;

public class Player {

    private String name;
    private int chips;
    private Hand hand;
    private int currentBet;
    private boolean folded;
    private boolean allIn;

    Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        this.hand = new Hand();
        this.currentBet = 0;
        this.folded = false;
        this.allIn = false;
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

    public void makeDecision(int potCurrentBet, Pot pot, Scanner scanner) {
        System.out.println("--- Pass to " + name + " and press Enter ---");
        scanner.nextLine();
        System.out.println("Your hole cards:");
        System.out.println(Card.renderCards(hand.getHoleCards()));

        while (true) {
            System.out.println(name + ", your current chips: " + chips);
            System.out.println("Current bet to call: " + potCurrentBet);
            System.out.println("Pot size: " + pot.getPotSize());
            System.out.print("Enter your action (fold, call, check, raise): ");
            String action = scanner.nextLine().toLowerCase();
            switch (action) {
                case "fold":
                    fold();
                    System.out.println();
                    return;
                case "check":
                    if (potCurrentBet > this.currentBet) {
                        System.out.println(
                            "You cannot check — there is a bet to call."
                        );
                        break;
                    }
                    System.out.println();
                    return;
                case "call":
                    placeBet(Math.min(potCurrentBet - this.currentBet, chips));
                    System.out.println();
                    return;
                case "raise":
                    System.out.print("Enter total chips to put in (min " + (potCurrentBet - this.currentBet + 1) + ", max " + chips + "): ");
                    int raiseAmount = scanner.nextInt();
                    scanner.nextLine();
                    if (raiseAmount < (potCurrentBet - this.currentBet + 1) || raiseAmount > chips) {
                        System.out.println("Invalid raise amount.");
                        break;
                    }
                    placeBet(raiseAmount);
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }
}
