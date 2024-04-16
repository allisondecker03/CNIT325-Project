package User;

import Frames.LoginFrame;

import java.net.Socket;
import java.util.ResourceBundle;

public class Client {
    public static ResourceBundle languageBundle = ResourceBundle.getBundle("languages/english");
    
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            new LoginFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
