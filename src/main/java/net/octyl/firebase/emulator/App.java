package net.octyl.firebase.emulator;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {

    private static final String FIRESTORE_EMULATOR_SYSTEM_VARIABLE = "FIRESTORE_EMULATOR_HOST";

    public static void main(String[] args) throws Exception {
        System.err.println(FIRESTORE_EMULATOR_SYSTEM_VARIABLE + ": " + System.getenv(FIRESTORE_EMULATOR_SYSTEM_VARIABLE));

        var options = FirebaseOptions.builder()
            .setCredentials(getCredentials())
            .build();

        var firebaseApp = FirebaseApp.initializeApp(options);
        try (var firestore = FirestoreClient.getFirestore(firebaseApp)) {
            firestore.document("test/test").get().get();
        }
        System.err.println("Successfully got item.");
    }

    private static GoogleCredentials getCredentials() throws IOException {
        try (var stream = Files.newInputStream(Path.of("./secrets/firebase-adminsdk.json"))) {
            return GoogleCredentials.fromStream(stream);
        }
    }
}
