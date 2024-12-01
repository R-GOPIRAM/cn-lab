import java.io.*;
import java.net.*;

public class EchoServer {
    private ServerSocket server;

    public EchoServer(int portnum) {
        try {
            server = new ServerSocket(portnum);
            System.out.println("EchoServer is up and running on port " + portnum);
        } catch (IOException err) {
            System.err.println("Server setup error: " + err.getMessage());
        }
    }

    public void serve() {
        try {
            while (true) {
                Socket client = server.accept();
                System.out.println("Client connected: " + client.getInetAddress() + ":" + client.getPort());
                new ClientHandler(client).start();
            }
        } catch (IOException err) {
            System.err.println("Server error: " + err.getMessage());
        }
    }

    public static void main(String[] args) {
        EchoServer s = new EchoServer(9999);
        s.serve();
    }

    private static class ClientHandler extends Thread {
        private Socket client;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        public void run() {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter w = new PrintWriter(client.getOutputStream(), true)) {

                w.println("Welcome to the Java EchoServer. Type 'bye' to close.");
                String line;
                while ((line = r.readLine()) != null) {
                    if (line.trim().equalsIgnoreCase("bye")) {
                        break;
                    }
                    w.println("Got: " + line);
                    System.out.println("Received: " + line);
                }
            } catch (IOException err) {
                System.err.println("ClientHandler error: " + err.getMessage());
            } finally {
                try {
                    client.close();
                } catch (IOException err) {
                    System.err.println("Error closing client socket: " + err.getMessage());
                }
            }
        }
    }
}
