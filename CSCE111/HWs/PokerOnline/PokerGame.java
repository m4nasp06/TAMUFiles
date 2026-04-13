import java.util.ArrayList;

public class PokerGame {

    private ArrayList<Player> players;
    private Deck deck;
    private Pot pot;
    private ArrayList<Card> community;
    private int dealer;
    private int smallBlind;
    private int bigBlind;

    PokerGame(ArrayList<Player> players, int smallBlind) {
        this.players = players;
        this.deck = new Deck();
        this.pot = new Pot();
        this.community = new ArrayList<Card>();
        this.dealer = 0;
        this.smallBlind = smallBlind;
        this.bigBlind = smallBlind * 2;
    }

    private void broadcast(String msg) {
        for (Player p : players) {
            p.sendMessage(msg);
        }
    }

    public void startGame() {
        while (players.size() > 1) {
            int smallBlindIdx = (dealer + 1) % players.size();
            Player dealerPlayer = players.get(dealer);
            playHand(smallBlindIdx);
            eliminateBroke();
            if (players.size() > 1) {
                int newDealerIdx = players.indexOf(dealerPlayer);
                if (newDealerIdx >= 0) {
                    dealer = (newDealerIdx + 1) % players.size();
                } else {
                    dealer = dealer % players.size();
                }
            }
        }
        broadcast("Game Over! Winner: " + players.get(0).getName());
    }

    public void playHand(int smallBlindIdx) {
        deck = new Deck();
        deck.shuffle();
        community.clear();
        pot.reset();
        for (Player p : players) {
            p.resetNewRound();
        }
        postBlinds(smallBlindIdx);
        dealHoleCards();
        displayCommunity();
        bettingRound((smallBlindIdx + 2) % players.size());

        dealCommunityCards(3);
        resetStreet();
        displayCommunity();
        bettingRound(smallBlindIdx);

        dealCommunityCards(1);
        resetStreet();
        displayCommunity();
        bettingRound(smallBlindIdx);

        dealCommunityCards(1);
        resetStreet();
        displayCommunity();
        bettingRound(smallBlindIdx);

        showdown();
    }

    private void displayCommunity() {
        if (community.isEmpty()) return;
        broadcast("Community cards: ");
        broadcast(Card.renderCards(community));
    }

    private void resetStreet() {
        pot.setCurrentBet(0);
        for (Player p : players) {
            p.resetBet();
        }
    }

    public void eliminateBroke() {
        for (int i = players.size() - 1; i >= 0; i--) {
            if (players.get(i).getChips() <= 0) {
                broadcast(players.get(i).getName() + " is eliminated!");
                players.remove(i);
            }
        }
    }

    public void postBlinds(int smallBlindIdx) {
        int bigBlindIdx = (smallBlindIdx + 1) % players.size();
        int sbActual = Math.min(smallBlind, players.get(smallBlindIdx).getChips());
        players.get(smallBlindIdx).placeBet(sbActual);
        broadcast(players.get(smallBlindIdx).getName() + " posts small blind: " + sbActual);
        int bbActual = Math.min(bigBlind, players.get(bigBlindIdx).getChips());
        players.get(bigBlindIdx).placeBet(bbActual);
        broadcast(players.get(bigBlindIdx).getName() + " posts big blind: " + bbActual);
        pot.addChips(sbActual + bbActual);
        pot.setCurrentBet(bbActual);
    }

    public void dealHoleCards() {
        for (Player p : players) {
            p.getPlayerHand().addCard(deck.dealCard());
            p.getPlayerHand().addCard(deck.dealCard());
        }
    }

    public void bettingRound(int startIdx) {
        int active = 0;
        for (Player p : players) if (!p.isFolded()) active++;
        if (active <= 1) return;

        int potCurrentBet = pot.getCurrentBet();
        boolean raised = true;
        while (raised) {
            raised = false;
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get((startIdx + i) % players.size());
                if (!p.isFolded() && !p.isAllIn()) {
                    int betBefore = p.getCurrentBet();
                    p.makeDecision(potCurrentBet, pot);
                    pot.addChips(p.getCurrentBet() - betBefore);
                    if (p.getCurrentBet() > potCurrentBet) {
                        potCurrentBet = p.getCurrentBet();
                        pot.setCurrentBet(potCurrentBet);
                        raised = true;
                    }
                    int activePlayers = 0;
                    for (Player pl : players) if (!pl.isFolded()) activePlayers++;
                    if (activePlayers <= 1) return;
                }
            }
        }
    }

    public void dealCommunityCards(int num) {
        deck.dealCard(); // burn
        for (int i = 0; i < num; i++) {
            community.add(deck.dealCard());
        }
    }

    public void showdown() {
        HandEvaluator evaluator = new HandEvaluator();
        int bestValue = -1;

        for (Player p : players) {
            if (!p.isFolded()) {
                int value = evaluator.evaluate(p.getPlayerHand(), community);
                if (value > bestValue) bestValue = value;
            }
        }

        ArrayList<Player> winners = new ArrayList<Player>();
        for (Player p : players) {
            if (!p.isFolded() && p.getPlayerHand().getHandValue() == bestValue) {
                winners.add(p);
            }
        }

        if (winners.size() > 1) {
            ArrayList<Player> trueWinners = new ArrayList<Player>();
            trueWinners.add(winners.get(0));
            for (int i = 1; i < winners.size(); i++) {
                int cmp = evaluator.compareHands(
                    winners.get(i).getPlayerHand().getYourBestHand(),
                    trueWinners.get(0).getPlayerHand().getYourBestHand(),
                    bestValue
                );
                if (cmp > 0) {
                    trueWinners.clear();
                    trueWinners.add(winners.get(i));
                } else if (cmp == 0) {
                    trueWinners.add(winners.get(i));
                }
            }
            winners = trueWinners;
        }

        if (winners.isEmpty()) return;
        int split = pot.getPotSize() / winners.size();
        if (winners.size() == 1) {
            broadcast(winners.get(0).getName() + " wins the pot of " + pot.getPotSize() + " chips!");
            broadcast("Winning hand: " + HandEvaluator.HAND_NAMES[bestValue]);
            broadcast(Card.renderCards(winners.get(0).getPlayerHand().getYourBestHand()));
        } else {
            broadcast("Tie! Splitting pot of " + pot.getPotSize() + " chips between " + winners.size() + " players.");
            broadcast("Tied hand: " + HandEvaluator.HAND_NAMES[bestValue]);
        }
        for (Player w : winners) {
            w.receiveChips(split);
        }
        for (Player p : players) {
            broadcast(p.getName() + ": " + p.getChips() + " chips");
        }
    }
}
