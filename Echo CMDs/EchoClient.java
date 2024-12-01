import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try (Socket s = new Socket("127.0.0.1", 9999);
             BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter w = new PrintWriter(s.getOutputStream(), true);
             BufferedReader con = new BufferedReader(new InputStreamReader(System.in))) {

            String line;
            while ((line = r.readLine()) != null) {
                System.out.println(line);
                if (line.trim().equalsIgnoreCase("bye")) {
                    break;
                }
                System.out.println("SEND (Type 'bye' to Quit):");
                line = con.readLine();
                w.println(line);
                if (line.trim().equalsIgnoreCase("bye")) {
                    break;
                }
            }
        } catch (IOException err) {
            System.err.println("Client error: " + err.getMessage());
        }
    }
}
