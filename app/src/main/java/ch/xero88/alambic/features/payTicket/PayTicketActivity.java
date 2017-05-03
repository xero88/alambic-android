package ch.xero88.alambic.features.payTicket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;

import ch.xero88.alambic.R;
import ch.xero88.alambic.firebase.ServicesManager;

public class PayTicketActivity extends AppCompatActivity implements PayTicketContract.View {

    private String TAG = "PayTicketActivity";

    // ui
    private CardInputWidget mCardInputWidget;

    // presenter
    private PayTicketPresenter presenter;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ticket);

        // init presenter
        presenter = new PayTicketPresenter(this, this, ServicesManager.getInstance().getStripeService());

        // ui
        mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutPayTicket);
    }

    public void onPayButtonClick(View view) {

        // valid card
        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
            showErrorOnStripe();
            return;
        }

        presenter.sendStripeTokenToBackend(cardToSave);
    }

    @Override
    public void showErrorOnStripe() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.error_on_payment), Snackbar.LENGTH_LONG);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void cardSuccessfulAdded() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.card_added_successful), Snackbar.LENGTH_LONG);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.GREEN);
        snackbar.show();
    }
}
