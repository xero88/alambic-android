package ch.xero88.alambic.features.giftList;

import java.util.ArrayList;

import ch.xero88.alambic.firebase.model.Gift;

public class GiftListContract {
    interface View {
        void updateGifts(ArrayList<Gift> gifts);
        void notEnoughPoints();
        void pleaseRetry();
        void giftBought();
    }

    interface UserActionsListener {
        void buyGift(Gift clickedGift);
    }
}
