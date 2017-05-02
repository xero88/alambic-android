package ch.xero88.alambic.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.firebase.model.Gift;

public class GiftService {

    private static final String TAG = "GiftService";

    // refs
    private DatabaseReference mGiftRef;

    public GiftService(){
        mGiftRef = AlambicApp.getInstance().getDatabase().getReference("gifts");
    }

    public void getAllGifts(final GiftCallback callback){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Gift> giftsList = new ArrayList<>();
                for (DataSnapshot giftSnapshot: dataSnapshot.getChildren()) {
                    Gift gift = giftSnapshot.getValue(Gift.class);
                    gift.setId(giftSnapshot.getKey()); // set id of Gift

                    giftsList.add(gift);
                }
                callback.getAllGifts(giftsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mGiftRef
                .addValueEventListener(postListener);

    }

    public interface GiftCallback{
        void getAllGifts(ArrayList<Gift> giftsList);
    }

}
