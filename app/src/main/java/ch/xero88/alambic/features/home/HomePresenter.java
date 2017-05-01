package ch.xero88.alambic.features.home;

import android.support.v7.app.AppCompatActivity;

import ch.xero88.alambic.AlambicApp;

public class HomePresenter implements HomeContract.UserActionsListener {

    HomeContract.View mView;

    public HomePresenter(HomeContract.View view, AppCompatActivity activity) {
        mView = view;
    }

    public void init(){
        // update user name
        mView.updateWelcome(AlambicApp.getInstance().getCurrentUser().getDisplayName());
    }
}
