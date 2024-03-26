import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            while (true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
