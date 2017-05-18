package ch.xero88.alambic.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseUser;

import ch.xero88.alambic.firebase.model.Gift;

public class FIRLogging {

    private final FirebaseAnalytics mFirebaseAnalytics;

    public FIRLogging(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void login(FirebaseUser user){

        Bundle bundle = new Bundle();
        if(user != null && user.getEmail() != null)
            bundle.putString(FirebaseAnalytics.Param.VALUE, user.getEmail());

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }

    public void buyGift(Gift gift){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, gift.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, gift.getName());
        bundle.putString(FirebaseAnalytics.Param.PRICE, gift.getPoints() + "");

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SPEND_VIRTUAL_CURRENCY, bundle);
    }
}
