import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("TCPServer Waiting for client on port 5000");
            
            while (true) {
                try (Socket connected = serverSocket.accept();
                     BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));
                     PrintWriter outToClient = new PrintWriter(connected.getOutputStream(), true);
                     BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in))) {

                    System.out.println("THE CLIENT " + connected.getInetAddress() + ":" + connected.getPort() + " IS CONNECTED");

                    String fromClient, toClient;
                    while (true) {
                        System.out.println("SEND (Type Q or q to Quit):");
                        toClient = inFromUser.readLine();
                        if (toClient.equalsIgnoreCase("q")) {
                            outToClient.println(toClient);
                            break;
                        } else {
                            outToClient.println(toClient);
                        }
                        fromClient = inFromClient.readLine();
                        if (fromClient == null || fromClient.equalsIgnoreCase("q")) {
                            break;
                        } else {
                            System.out.println("RECEIVED: " + fromClient);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Connection Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }
}
