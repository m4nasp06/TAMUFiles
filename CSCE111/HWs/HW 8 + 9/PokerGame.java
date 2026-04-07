import java.util.ArrayList;
import java.util.Scanner;

public class PokerGame {

    private ArrayList<Player> players;
    private Deck deck;
    private Pot pot;
    private ArrayList<Card> community;
    private int dealer;
    private int smallBlind;
    private int bigBlind;
    private Scanner scanner;

    PokerGame(ArrayList<Player> players, int smallBlind, Scanner scanner) {
        this.players = players;
        this.deck = new Deck();
        this.pot = new Pot();
        this.community = new ArrayList<Card>();
        this.dealer = 0;
        this.smallBlind = smallBlind;
        this.bigBlind = smallBlind * 2;
        this.scanner = scanner;
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
                    // Dealer survived — advance from their current index
                    dealer = (newDealerIdx + 1) % players.size();
                } else {
                    // Dealer was eliminated — the next player inherits the position
                    dealer = dealer % players.size();
                }
            }
        }

        System.out.println("Game Over! Winner: " + players.get(0).getName());
    }

    public void playHand(int smallBlindIdx) {
        deck = new Deck();
        deck.shuffle();
        community.clear();
        pot.reset();
        // use player.resetNewRound for all players
        for (Player p : players) {
            p.resetNewRound();
        }
        postBlinds(smallBlindIdx);
        dealHoleCards();
        displayCommunity();
        bettingRound((smallBlindIdx + 2) % players.size()); // pre-flop: left of BB

        dealCommunityCards(3); // flop
        resetStreet();
        displayCommunity();
        bettingRound(smallBlindIdx); // SB acts first

        dealCommunityCards(1); // turn
        resetStreet();
        displayCommunity();
        bettingRound(smallBlindIdx);

        dealCommunityCards(1); // river
        resetStreet();
        displayCommunity();
        bettingRound(smallBlindIdx);

        showdown();
    }

    private void displayCommunity() {
        if (community.isEmpty()) return;
        System.out.println("Community cards: ");
        // if (community.isEmpty()) {
        //     System.out.println("[none yet]");
        //     return;
        // }
        // else {
        //     for (Card c : community) System.out.print(c + " ");
        //     System.out.println();
        // }
        System.out.println(Card.renderCards(community));
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
                System.out.println(
                    players.get(i).getName() + " is eliminated!"
                );
                players.remove(i);
            }
        }
    }

    public void postBlinds(int smallBlindIdx) {
        int bigBlindIdx = ((smallBlindIdx + 1) % players.size());
        int sbActual = Math.min(
            smallBlind,
            players.get(smallBlindIdx).getChips()
        );
        players.get(smallBlindIdx).placeBet(sbActual);
        System.out.println(
            players.get(smallBlindIdx).getName() +
                " posts small blind: " +
                sbActual
        );
        int bbActual = Math.min(bigBlind, players.get(bigBlindIdx).getChips());
        players.get(bigBlindIdx).placeBet(bbActual);
        System.out.println(
            players.get(bigBlindIdx).getName() + " posts big blind: " + bbActual
        );
        pot.addChips(sbActual + bbActual);
        pot.setCurrentBet(bigBlind);
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
                    p.makeDecision(potCurrentBet, pot, scanner);
                    pot.addChips(p.getCurrentBet() - betBefore);
                    if (p.getCurrentBet() > potCurrentBet) {
                        potCurrentBet = p.getCurrentBet();
                        pot.setCurrentBet(potCurrentBet);
                        raised = true;
                    }
                    int activePlayers = 0;
                    for (Player pl : players) {
                        if (!pl.isFolded()) activePlayers++;
                    }
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

        // find best hand value
        for (Player p : players) {
            if (!p.isFolded()) {
                int value = evaluator.evaluate(p.getPlayerHand(), community);
                if (value > bestValue) bestValue = value;
            }
        }

        // collect all players tied at best value
        ArrayList<Player> winners = new ArrayList<Player>();
        for (Player p : players) {
            if (
                !p.isFolded() && p.getPlayerHand().getHandValue() == bestValue
            ) {
                winners.add(p);
            }
        }

        // break ties by comparing cards (kicker / high card)
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
            System.out.println(
                winners.get(0).getName() +
                    " wins the pot of " +
                    pot.getPotSize() +
                    " chips!"
            );
            // Print both players chip count
            // for (Player p : players) {
            //     System.out.println(
            //         "Player: " +
            //             p.getName() +
            //             "has this amount of chips:" +
            //             p.getChips()
            //     );
            // }
            // Print winning hand
            System.out.println(
                "Winning hand: " + HandEvaluator.HAND_NAMES[bestValue]
            );

            System.out.println(
                Card.renderCards(
                    winners.get(0).getPlayerHand().getYourBestHand()
                )
            );

            System.out.println();
        } else {
            System.out.println(
                "Tie! Splitting pot of " +
                    pot.getPotSize() +
                    " chips between " +
                    winners.size() +
                    " players."
            );
            System.out.println(
                "Tied hand: " + HandEvaluator.HAND_NAMES[bestValue]
            );
        }
        for (Player w : winners) {
            w.receiveChips(split);
        }
    }
}
