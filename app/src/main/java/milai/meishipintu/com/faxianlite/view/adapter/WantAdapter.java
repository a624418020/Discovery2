package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.CanScrollItemView;
import milai.meishipintu.com.faxianlite.Tool.ChooseHeadViewDialog;
import milai.meishipintu.com.faxianlite.model.beans.WantItem;
import milai.meishipintu.com.faxianlite.view.activity.OnItemClickListener;
import milai.meishipintu.com.faxianlite.view.activity.WantActivity;

/**
 * Created by tangyangkai on 16/6/12.
 */


public class WantAdapter extends RecyclerView.Adapter<WantedViewHolder>  {

    private Context mContext;
    private List<WantItem> mData;
    private RequestManager requestManager;
    private OnItemClickListener listenter;

    public WantAdapter(Context context, List<WantItem> wantItemList) {
        mContext = context;
        mData = wantItemList;
        requestManager = Glide.with(mContext);
        this.listenter = (OnItemClickListener) mContext;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public WantedViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_want, arg0, false);
        WantedViewHolder holder = new WantedViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final WantedViewHolder holder, final int position) {
        final int positionNow = position;
        final WantItem wantItem = mData.get(position);
        holder.tvTitle.setText(wantItem.getTitle());
        requestManager.load("http://" + wantItem.getLogo()).error(R.drawable.default_samll_square).into(holder.commodityImage);
        holder.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenter.onItemRemove(positionNow);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenter.onItemClick(wantItem);
            }
        });
//        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mData.remove(position);
//                WantAdapter.this.notifyItemRemoved(position);
//                WantAdapter.this.notifyItemChanged(position, mData.size() - position);
//            }
//        });
    }

    public void remove(WantItem wantItem) {
        mData.remove(wantItem);
        ;
    }

}