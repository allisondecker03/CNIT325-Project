import Frames.LoginFrame;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Client {
    
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            new LoginFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
