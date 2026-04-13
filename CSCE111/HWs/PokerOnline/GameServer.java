import java.net.*;
import java.io.*;
import java.util.*;

public class GameServer {

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);

        System.out.println("=== Texas Hold'em Poker Server ===");

        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > 6) {
            System.out.print("Enter number of players (2-6): ");
            numPlayers = console.nextInt();
            console.nextLine();
            if (numPlayers < 2 || numPlayers > 6) System.out.println("Must be between 2 and 6.");
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

        System.out.print("Enter port (press Enter for default 5000): ");
        String portInput = console.nextLine().trim();
        int port = portInput.isEmpty() ? 5000 : Integer.parseInt(portInput);

        ArrayList<Player> players = new ArrayList<>();

        // Player 1 = server host, uses local stdin/stdout
        System.out.print("Enter your name (you are Player 1): ");
        String hostName = console.nextLine();
        Player hostPlayer = new Player(hostName, startingChips);
        PrintWriter hostOut = new PrintWriter(System.out, true);
        BufferedReader hostIn = new BufferedReader(new InputStreamReader(System.in));
        hostPlayer.setStreams(hostOut, hostIn, false);
        players.add(hostPlayer);

        // Wait for remaining players to connect
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("\nWaiting for " + (numPlayers - 1) + " more player(s) on port " + port + "...");
        System.out.println("Tell your friends to run:  java GameClient <your-ip> " + port + "\n");

        for (int i = 2; i <= numPlayers; i++) {
            Socket socket = serverSocket.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("=== Texas Hold'em Poker ===");
            out.println("Connected! You are Player " + i + ".");
            out.println("Enter your name: ");
            out.println("INPUT_REQUIRED");
            String nameInput = in.readLine();
            String name = (nameInput != null && !nameInput.trim().isEmpty()) ? nameInput.trim() : "Player" + i;

            Player p = new Player(name, startingChips);
            p.setStreams(out, in, true);
            players.add(p);

            String joinMsg = name + " has joined. (" + i + "/" + numPlayers + " players connected)";
            System.out.println(joinMsg);
            for (Player existing : players) {
                existing.sendMessage(joinMsg);
            }
        }

        serverSocket.close();

        String startMsg = "\nAll players connected! Starting game — " + numPlayers +
            " players, " + startingChips + " chips each, blinds " +
            smallBlind + "/" + (smallBlind * 2) + "\n";
        for (Player p : players) p.sendMessage(startMsg);

        PokerGame game = new PokerGame(players, smallBlind);
        game.startGame();
    }
}
