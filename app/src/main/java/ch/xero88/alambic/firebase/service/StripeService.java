package ch.xero88.alambic.firebase.service;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.stripe.android.model.Token;

import ch.xero88.alambic.AlambicApp;

public class StripeService {

    private static final String TAG = "StripeService";

    private static class StripeField{
        public static final String STRIPE_TOKEN = "stripe_token";
    }

    // refs
    private DatabaseReference mStripeRef;

    public StripeService(){
        mStripeRef = AlambicApp.getInstance().getDatabase().getReference("stripe");
    }

    /**
     * Save stripe token for user
     */
    public void saveToken(FirebaseUser user, Token token, final TokenSavedCallback callback){

        mStripeRef.child(user.getUid()).child(StripeField.STRIPE_TOKEN).setValue(token, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if(databaseError != null)
                    callback.errorOnTokenSaved(databaseError.toString());

                callback.tokenSaved();
            }
        });
    }

    public interface TokenSavedCallback{
        void tokenSaved();
        void errorOnTokenSaved(String error);
    }

}
