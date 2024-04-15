import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("In the run method of a client handler");
        // Receive String from client
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                String message = br.readLine();
                if (message != null) {
                    System.out.println(message);
                    PrintWriter pw = new PrintWriter(socket.getOutputStream());
                    pw.println(message);
                    pw.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //scanner.close();
    }
}
