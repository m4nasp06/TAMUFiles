import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Runner {

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);

        System.out.println("=== Texas Hold'em Poker ===");

        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > 6) {
            System.out.print("Enter number of players (2-6): ");
            numPlayers = console.nextInt();
            console.nextLine();
            if (numPlayers < 2 || numPlayers > 6) System.out.println(
                "Must be between 2 and 6."
            );
        }

        int startingChips = 0;
        while (startingChips <= 0) {
            System.out.print("Enter starting chips per player: ");
            startingChips = console.nextInt();
            console.nextLine();
        }

        int smallBlind = 0;
        while (smallBlind <= 0) {
            System.out.print("Enter small blind amount: ");
            smallBlind = console.nextInt();
            console.nextLine();
        }

        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String name = console.nextLine();
            players.add(new Player(name, startingChips));
        }

        System.out.println(
            "\nStarting game with " + numPlayers + " players, " + startingChips +
            " chips each, blinds " + smallBlind + "/" + (smallBlind * 2) + "\n"
        );

        // All local players share the same stdout and stdin
        PrintWriter sharedOut = new PrintWriter(System.out, true);
        BufferedReader sharedIn = new BufferedReader(new InputStreamReader(System.in));
        for (Player p : players) {
            p.setStreams(sharedOut, sharedIn, false);
        }

        PokerGame game = new PokerGame(players, smallBlind);
        game.startGame();
    }
}
