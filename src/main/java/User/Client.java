package User;

import Frames.LoginFrame;

import java.net.Socket;

public class Client {
    
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            new LoginFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
