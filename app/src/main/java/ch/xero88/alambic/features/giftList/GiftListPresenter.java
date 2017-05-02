package ch.xero88.alambic.features.giftList;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.firebase.model.Member;
import ch.xero88.alambic.firebase.service.GiftService;
import ch.xero88.alambic.firebase.service.MemberService;
import ch.xero88.alambic.firebase.model.Gift;

class GiftListPresenter implements GiftListContract.UserActionsListener {

    private final GiftListContract.View mView;
    private final AppCompatActivity mActivity;

    // svc
    private final GiftService mGiftSvc;
    private final MemberService mMemberSvc;

    GiftListPresenter(GiftListContract.View view, AppCompatActivity activity, GiftService giftService, MemberService memberService) {
        mView = view;
        mActivity = activity;
        mGiftSvc = giftService;
        mMemberSvc = memberService;

        // get all gifts
        mGiftSvc.getAllGifts(new GiftService.GiftCallback() {
            @Override
            public void getAllGifts(ArrayList<Gift> giftsList) {
                mView.updateGifts(giftsList);
            }
        });
    }

    @Override
    public void buyGift(Gift clickedGift) {

        FirebaseUser currentUser = AlambicApp.getInstance().getCurrentUser();
        Member currentMember = AlambicApp.getInstance().getCurrentMember();
        if(currentUser == null || currentMember == null){
            mView.pleaseRetry();
            return;
        }

        // check if user had enough points
        if(clickedGift.getPoints() > currentMember.getAvailablePoints()){
            mView.notEnoughPoints();
            return;
        }

        // try to buy gift
        mMemberSvc.buyGift(clickedGift, currentUser, new MemberService.GiftBoughtCallback() {
            @Override
            public void giftBought() {
                mView.giftBought();
            }

            @Override
            public void errorOnBuy() {
                mView.pleaseRetry();
            }
        });
    }
}
