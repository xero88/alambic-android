package ch.xero88.alambic.features.giftList;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ch.xero88.alambic.R;
import ch.xero88.alambic.features.giftList.ui.GiftAdapter;
import ch.xero88.alambic.firebase.ServicesManager;
import ch.xero88.alambic.firebase.model.Gift;

public class GiftListActivity extends AppCompatActivity implements GiftListContract.View, View.OnClickListener {

    // ui
    private RecyclerView mRecyclerView;
    private GiftAdapter mAdapter;
    private CoordinatorLayout mCoordinatorLayout;

    // presenter
    private GiftListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list);

        // presenter
        presenter = new GiftListPresenter(this, this,
                ServicesManager.getInstance().getGiftService(),
                ServicesManager.getInstance().getMemberService());

        initUI();
    }

    private void initUI() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // specify an adapter
        mAdapter = new GiftAdapter(null, R.layout.card_view_gift, getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateGifts(ArrayList<Gift> gifts) {
        mAdapter.updateGifts(gifts);
    }

    @Override
    public void notEnoughPoints() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.not_enough_points), Snackbar.LENGTH_LONG);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void pleaseRetry() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.please_retry_later), Snackbar.LENGTH_LONG);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void giftBought() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.gift_bought), Snackbar.LENGTH_LONG);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.GREEN);
        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
        Gift clickedGift = mAdapter.getGiftsList().get(itemPosition);
        presenter.buyGift(clickedGift);
    }
}
