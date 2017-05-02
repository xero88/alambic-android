package ch.xero88.alambic.features.giftList.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ch.xero88.alambic.R;
import ch.xero88.alambic.firebase.model.Gift;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.ViewHolder> {

    private int mItemLayout;
    private ArrayList<Gift> mGiftsDataSet;

    public void updateGifts(ArrayList<Gift> gifts) {
        mGiftsDataSet = gifts;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageGift;
        TextView pointsGift;
        TextView nameGift;

        ViewHolder(View itemView) {
            super(itemView);
            imageGift = (ImageView) itemView.findViewById(R.id.gift_image);
            nameGift = (TextView) itemView.findViewById(R.id.gift_name);
            pointsGift = (TextView) itemView.findViewById(R.id.gift_points);
        }
    }

    public GiftAdapter(ArrayList<Gift> myDataset, int itemLayout) {
        mGiftsDataSet = myDataset;
        mItemLayout = itemLayout;
    }

    @Override
    public GiftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameGift.setText(mGiftsDataSet.get(position).getName());
        holder.pointsGift.setText(mGiftsDataSet.get(position).getPoints() + "");
    }

    @Override
    public int getItemCount() {
        if(mGiftsDataSet == null)
            return 0;

        return mGiftsDataSet.size();
    }
}