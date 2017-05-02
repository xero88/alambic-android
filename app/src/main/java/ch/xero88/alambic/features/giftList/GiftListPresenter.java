package ch.xero88.alambic.features.giftList;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import ch.xero88.alambic.firebase.GiftService;
import ch.xero88.alambic.firebase.model.Gift;

class GiftListPresenter implements GiftListContract.UserActionsListener {

    private final GiftListContract.View mView;
    private final AppCompatActivity mActivity;

    // svc
    private final GiftService mGiftSvc;

    GiftListPresenter(GiftListContract.View view, AppCompatActivity activity, GiftService giftService) {
        mView = view;
        mActivity = activity;
        mGiftSvc = giftService;

        // get all gifts
        mGiftSvc.getAllGifts(new GiftService.GiftCallback() {
            @Override
            public void getAllGifts(ArrayList<Gift> giftsList) {
                mView.updateGifts(giftsList);
            }
        });
    }
}
