package ch.xero88.alambic.features.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.R;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    // presenter
    private HomePresenter presenter;

    // ui
    private TextView welcomeTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // presenter
        presenter = new HomePresenter(this, this);

        // ui
        welcomeTxtView = (TextView) findViewById(R.id.welcomeTxtView);
        presenter.init();
    }

    public void updateWelcome(@NonNull String username){
        welcomeTxtView.setText(getString(R.string.welcome) + " " + username);
    }
}
