package Firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseUtilities {
	
	public final static FirebaseUtilities firebaseUtilities= new FirebaseUtilities();
	private static Firestore db;
	
	public FirebaseUtilities() {
		try {
			FileInputStream serviceAccount =
					new FileInputStream("src/main/resources/Firebase/ServiceAccountKey.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					                          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					                          .build();
			FirebaseApp.initializeApp(options);
			db = FirestoreClient.getFirestore();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getAllUsernames() {
		ApiFuture<QuerySnapshot> snapshot = db.collection("USER").get();
		ArrayList<String> usernames = new ArrayList<>();
		try {
			QuerySnapshot snap = snapshot.get();
			var docs = snap.getDocuments();
			for (QueryDocumentSnapshot doc : docs) {
				usernames.add((String) doc.get("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usernames;
	}
	
	public static void createNewUser(HashMap<String, String> newUserData) {
		db.collection("USER").document().set(newUserData);
	}
}
