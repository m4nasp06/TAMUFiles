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

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public Hand getHand() {
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

    public void makeDecision(int potCurrentBet, Pot pot, Scanner scanner) {
        System.out.print("--- Pass to " + name + " and press Enter ---");
        scanner.nextLine();
        System.out.print("Your hole cards: ");
        for (Card c : hand.getHoleCards()) System.out.print(c + " ");
        System.out.println();

        while (true) {
            System.out.println(name + ", your current chips: " + chips);
            System.out.println("Current bet to call: " + potCurrentBet);
            System.out.println("Pot size: " + pot.getPotSize());
            System.out.print("Enter your action (fold, call, check, raise): ");
            String action = scanner.nextLine().toLowerCase();
            switch (action) {
                case "fold":
                    fold();
                    return;
                case "check":
                    if (potCurrentBet > this.currentBet) {
                        System.out.println(
                            "You cannot check — there is a bet to call."
                        );
                        break;
                    }
                    return;
                case "call":
                    placeBet(potCurrentBet - this.currentBet);
                    return;
                case "raise":
                    System.out.print("Enter raise amount: ");
                    int raiseAmount = scanner.nextInt();
                    scanner.nextLine();
                    placeBet((potCurrentBet - this.currentBet) + raiseAmount);
                    return;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }
}
