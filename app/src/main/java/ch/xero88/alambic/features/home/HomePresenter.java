package ch.xero88.alambic.features.home;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.firebase.service.MemberService;

public class HomePresenter implements HomeContract.UserActionsListener {

    private final MemberService mMemberService;
    HomeContract.View mView;

    public HomePresenter(HomeContract.View view, AppCompatActivity activity, MemberService memberService) {
        mView = view;
        mMemberService = memberService;
    }

    @Override
    public void init(){

        // update user name
        mView.updateMemberName(AlambicApp.getInstance().getCurrentUser().getDisplayName());

        // update points
        mMemberService.getAvailablePoints(AlambicApp.getInstance().getCurrentUser(), new MemberService.PointsCallback() {
            @Override
            public void getNbPoints(int nbPoints) {
                mView.updatePoints(nbPoints);
            }

            @Override
            public void error(String error) {
                mView.showErrorCannotGetPoints(error);
            }
        });
    }

    @Override
    public void loggout(){
        FirebaseAuth.getInstance().signOut();
    }
}
