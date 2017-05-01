package ch.xero88.alambic.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import ch.xero88.alambic.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";

    // presenter
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // presenter
        presenter = new LoginPresenter(this /* View */, this /* Activity */);
    }

    public void updateUI(FirebaseUser user) {
        if(user != null)
            Log.e(TAG, "updateUI: " + user.getDisplayName());
    }

    public void showSignAuthFailed(){
        Log.e(TAG, "error on auth ");
        Toast.makeText(this, "Error on auth", Toast.LENGTH_SHORT).show();
    }

    public void onAuthButtonClick(View view) {
        presenter.signIn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.signInResult(requestCode, resultCode, data);
    }
}
