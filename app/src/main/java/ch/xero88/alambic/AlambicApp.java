package ch.xero88.alambic;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AlambicApp extends Application {

    /**
     * Instance of AlambicApp
     */
    private static AlambicApp instance;
    public static AlambicApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // firebase
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
    }

    /**
     * Firebase Instance
     */
    private FirebaseDatabase database;
    public FirebaseDatabase getDatabase() {
        return database;
    }

    /**
     * Current Firebase user
     */
    private FirebaseUser currentUser = null;

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }
}
