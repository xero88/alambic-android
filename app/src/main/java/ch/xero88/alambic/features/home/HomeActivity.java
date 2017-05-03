package ch.xero88.alambic.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ch.xero88.alambic.R;
import ch.xero88.alambic.features.giftList.GiftListActivity;
import ch.xero88.alambic.features.payTicket.PayTicketActivity;
import ch.xero88.alambic.firebase.ServicesManager;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    // presenter
    private HomePresenter presenter;

    // ui
    private TextView memberNameTxtView;
    private TextView pointsTxtView;

    // state
    private boolean backPressedTwice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // presenter
        presenter = new HomePresenter(this, this,
                ServicesManager.getInstance().getMemberService());

        // ui
        memberNameTxtView = (TextView) findViewById(R.id.memberNameTxtView);
        pointsTxtView = (TextView) findViewById(R.id.pointsTxtView);
        presenter.init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        backPressedTwice = false;
    }

    @Override
    public void updateMemberName(@NonNull String memberName){
        memberNameTxtView.setText(memberName);
    }

    @Override
    public void updatePoints(int nbPoints) {
        pointsTxtView.setText("" + nbPoints);
    }

    @Override
    public void showErrorCannotGetPoints(String error) {
        Toast.makeText(this, getString(R.string.cannot_get_the_points), Toast.LENGTH_SHORT).show();
    }

    public void onLoggoutClickButton(View view) {
        presenter.loggout();
        finish();
    }

    public void onBuyGiftClickButton(View view) {
        Intent intent = new Intent(this, GiftListActivity.class);
        startActivity(intent);
    }

    public void onPayTicketClickButton(View view) {
        Intent intent = new Intent(this, PayTicketActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // no back
        if(backPressedTwice) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show(); // TODO
        backPressedTwice = true;
    }

}
