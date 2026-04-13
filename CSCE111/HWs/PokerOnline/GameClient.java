import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5000;

        System.out.println("Connecting to " + host + ":" + port + "...");
        Socket socket = new Socket(host, port);
        System.out.println("Connected!\n");

        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner localInput = new Scanner(System.in);

        String line;
        while ((line = fromServer.readLine()) != null) {
            if (line.equals("INPUT_REQUIRED")) {
                toServer.println(localInput.nextLine());
            } else {
                System.out.println(line);
            }
        }

        System.out.println("\nDisconnected from server.");
        socket.close();
    }
}
