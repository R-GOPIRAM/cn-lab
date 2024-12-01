import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 5000);
             BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String fromServer, toServer;
            while (true) {
                fromServer = inFromServer.readLine();
                if (fromServer == null || fromServer.equalsIgnoreCase("q")) {
                    break;
                } else {
                    System.out.println("RECEIVED: " + fromServer);
                    System.out.println("SEND (Type Q or q to Quit):");
                    toServer = inFromUser.readLine();
                    if (toServer.equalsIgnoreCase("q")) {
                        outToServer.println(toServer);
                        break;
                    } else {
                        outToServer.println(toServer);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Client Error: " + e.getMessage());
        }
    }
}
