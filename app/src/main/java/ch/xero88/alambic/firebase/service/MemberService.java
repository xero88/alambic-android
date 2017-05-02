package ch.xero88.alambic.firebase.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.firebase.model.BoughtGift;
import ch.xero88.alambic.firebase.model.Gift;
import ch.xero88.alambic.firebase.model.Member;

public class MemberService {

    private static final String TAG = "MemberService";

    private static class MemberField{
        static String boughtGifts = "giftsBought";
    }

    // refs
    private DatabaseReference mMembersRef;

    public MemberService(){
        mMembersRef = AlambicApp.getInstance().getDatabase().getReference("members");
    }

    /**
     * Get current member
     */
    public void getMember(FirebaseUser user, final MemberCallback callback){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Member member = dataSnapshot.getValue(Member.class);
                callback.getCurrentMember(member);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mMembersRef
                .child(user.getUid())
                .addValueEventListener(postListener);
    }

    public interface MemberCallback{
        void getCurrentMember(Member member);
    }

    /**
     * Get available points of member
     */
    public void getAvailablePoints(FirebaseUser user, final PointsCallback callback){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Member member = dataSnapshot.getValue(Member.class);

                if(member == null) {
                    callback.getNbPoints(0);
                    return;
                }

                callback.getNbPoints(member.getAvailablePoints());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mMembersRef
                .child(user.getUid())
                .addValueEventListener(postListener);
    }

    /**
     * Get used points of member
     */
    public void getUsedPoints(FirebaseUser user, final PointsCallback callback){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Member member = dataSnapshot.getValue(Member.class);

                if(member == null) {
                    callback.getNbPoints(0);
                    return;
                }

                callback.getNbPoints(member.getUsedPoints());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mMembersRef
                .child(user.getUid())
                .addValueEventListener(postListener);
    }

    public interface PointsCallback{
        void getNbPoints(int nbPoints);
        void error(String error);
    }

    /**
     * Buy a gift
     *
     * Remarks : the points are removed by a functions (TODO)
     */
    public void buyGift(Gift gift, final FirebaseUser user, final GiftBoughtCallback callback){

        // gift to bought gift
        BoughtGift boughtGift = new BoughtGift(gift);
        boughtGift.setBoughtAt(new Date());

        // add gift to archive of member
        mMembersRef.child(user.getUid()).child(MemberField.boughtGifts).push().setValue(boughtGift, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                callback.giftBought();
            }
        });
    }

    public interface GiftBoughtCallback{
        void giftBought();
        void errorOnBuy();
    }

}
