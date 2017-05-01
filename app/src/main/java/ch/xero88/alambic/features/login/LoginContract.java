package ch.xero88.alambic.features.login;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface LoginContract {

    interface View {
        void updateUI(FirebaseUser user);
        void showSignAuthFailed();
    }

    interface UserActionsListener {
        void signIn();
        void signInResult(int requestCode, int resultCode, Intent data);
    }
}
