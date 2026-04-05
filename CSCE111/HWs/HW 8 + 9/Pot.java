public class Pot {

    private int totalChips;
    private int currentBet;

    Pot() {
        totalChips = 0;
        currentBet = 0;
    }

    public void addChips(int amount) {
        totalChips += amount;
    }

    public void setCurrentBet(int bet) {
        currentBet = bet;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public int getPotSize() {
        return totalChips;
    }

    public void reset() {
        totalChips = 0;
        currentBet = 0;
    }
}
