package ch.xero88.alambic.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import ch.xero88.alambic.R;
import ch.xero88.alambic.features.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";

    // presenter
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // presenter
        initPresenter();
    }

    private void initPresenter(){
        if(presenter == null)
            presenter = new LoginPresenter(this /* View */, this /* Activity */);
    }

    @Override
    public void showHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
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
