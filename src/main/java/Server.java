import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * A program that continuously accepts client connections and creates a new thread to handle each client.
 *
 * @author Colten Glover
 *
 * @version March 25, 2024, 7:50 PM
 */

public class Server {
    private static final int PORT_NUMBER = 8080;

    public static void main(String[] args) {
        // Socket listening for clients
        ServerSocket serverSocket;

        try {
            // Assign server socket to a port
            serverSocket = new ServerSocket(PORT_NUMBER);
        } catch (Exception e) {
            // TODO: Better handling
            e.printStackTrace();
            return;
        }

        while (true) {
            // Accept connection from client
            try (Socket s = serverSocket.accept()) {
                Thread thread = new Thread(new ClientHandler(s.getInputStream(), s.getOutputStream()));
                thread.start();
            } catch (IOException e) {
                // TODO: Better handling
                e.printStackTrace();
            }
        }
    }
}
