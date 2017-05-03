package ch.xero88.alambic.features.payTicket;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.Config;
import ch.xero88.alambic.firebase.service.StripeService;

public class PayTicketPresenter implements PayTicketContract.UserActionsListener {

    private static final String TAG = "PayTicketPresenter";

    // ui
    private final PayTicketContract.View mView;
    private final AppCompatActivity mActivity;

    // svc
    private final StripeService mStripeSvc;

    public PayTicketPresenter(PayTicketContract.View view, AppCompatActivity activity, StripeService stripeService) {
        mView = view;
        mActivity = activity;

        mStripeSvc = stripeService;
    }

    @Override
    public void sendStripeTokenToBackend(Card card) {
        Stripe stripe = new Stripe(mActivity, Config.STRIPE_PK);
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {

                        // Send token to your server
                        mStripeSvc.saveToken(AlambicApp.getInstance().getCurrentUser(), token, new StripeService.TokenSavedCallback() {
                            @Override
                            public void tokenSaved() {
                                mView.cardSuccessfulAdded();
                            }

                            @Override
                            public void errorOnTokenSaved(String error) {
                                mView.showErrorOnStripe();
                                Log.e(TAG, error);
                            }
                        });
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        mView.showErrorOnStripe();
                        Log.e(TAG, error.toString());
                    }
                }
        );
    }
}
