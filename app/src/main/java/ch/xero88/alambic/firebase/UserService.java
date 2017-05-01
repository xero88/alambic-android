package ch.xero88.alambic.firebase;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.firebase.model.User;

public class UserService {

    private static final String TAG = "UserService";

    // refs
    DatabaseReference mUserRef;

    public UserService(){
        mUserRef = AlambicApp.getInstance().getDatabase().getReference("users");
    }

    /**
     * Get points of user
     */
    public void getPoints(FirebaseUser user, final PointsCallback callback){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);

                if(user == null) {
                    callback.getNbPoints(0);
                    return;
                }

                callback.getNbPoints(user.getPoints());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mUserRef
                .child(user.getUid())
                .addValueEventListener(postListener);
    }

    public interface PointsCallback{
        void getNbPoints(int nbPoints);
        void error(String error);
    }

}
