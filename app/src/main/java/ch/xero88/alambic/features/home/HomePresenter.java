package ch.xero88.alambic.features.home;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ch.xero88.alambic.AlambicApp;
import ch.xero88.alambic.firebase.UserService;

public class HomePresenter implements HomeContract.UserActionsListener {

    private final UserService mUserService;
    HomeContract.View mView;

    public HomePresenter(HomeContract.View view, AppCompatActivity activity, UserService userService) {
        mView = view;
        mUserService = userService;
    }

    @Override
    public void init(){

        // update user name
        mView.updateMemberName(AlambicApp.getInstance().getCurrentUser().getDisplayName());

        // update points
        mUserService.getPoints(AlambicApp.getInstance().getCurrentUser(), new UserService.PointsCallback() {
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
