import java.io.*;
import java.util.*;

public class PingServer {
    public static void main(String[] args) {
        try {
            String str;
            System.out.print("Enter the IP Address to be pinged: ");
            BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));
            String ip = buf1.readLine();
            
            ProcessBuilder processBuilder = new ProcessBuilder("ping", ip);
            processBuilder.redirectErrorStream(true); // Combine error and input streams

            Process process = processBuilder.start();
            
            // Ensure input streams are closed after use
            try (BufferedReader buf2 = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while ((str = buf2.readLine()) != null) {
                    System.out.println(" " + str);
                }
            }
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
