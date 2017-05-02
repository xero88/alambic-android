package ch.xero88.alambic;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import ch.xero88.alambic.features.home.HomeActivity;
import ch.xero88.alambic.firebase.service.MemberService;
import ch.xero88.alambic.firebase.ServicesManager;
import ch.xero88.alambic.firebase.model.Member;

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
        storage = FirebaseStorage.getInstance();

        // check if logged
        checkIfLogged();
    }

    /**
     * Go to home activity if logged
     */
    private void checkIfLogged() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            // save user and member
            saveLoggedUser(currentUser);

            // go to home activity directly
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void saveLoggedUser(FirebaseUser user){
        AlambicApp.getInstance().setCurrentUser(user);
        ServicesManager.getInstance().getMemberService().getMember(user, new MemberService.MemberCallback() {
            @Override
            public void getCurrentMember(Member member) {
                AlambicApp.getInstance().setCurrentMember(member);
            }
        });
    }

    /**
     * Firebase Instance (db and storage)
     */
    private FirebaseDatabase database;
    public FirebaseDatabase getDatabase() {
        return database;
    }
    private FirebaseStorage storage;
    public FirebaseStorage getStorage() { return storage; }

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

    /**
     * Current Member
     */
    private Member currentMember = null;

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }
}
