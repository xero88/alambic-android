package ch.xero88.alambic.features.login;
import android.content.Intent;

public interface LoginContract {

    interface View {
        void showHomeActivity();
        void showSignAuthFailed();
    }

    interface UserActionsListener {
        void signIn();
        void signInResult(int requestCode, int resultCode, Intent data);
    }
}
