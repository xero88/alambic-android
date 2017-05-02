package ch.xero88.alambic.features.giftList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ch.xero88.alambic.R;
import ch.xero88.alambic.features.giftList.ui.GiftAdapter;
import ch.xero88.alambic.firebase.GiftService;
import ch.xero88.alambic.firebase.model.Gift;

public class GiftListActivity extends AppCompatActivity implements GiftListContract.View {

    // ui
    private RecyclerView mRecyclerView;
    private GiftAdapter mAdapter;

    // presenter
    private GiftListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list);

        // presenter
        presenter = new GiftListPresenter(this, this, new GiftService());

        initUI();
    }

    private void initUI() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // specify an adapter
        mAdapter = new GiftAdapter(null, R.layout.card_view_gift, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateGifts(ArrayList<Gift> gifts) {
        mAdapter.updateGifts(gifts);
    }
}
