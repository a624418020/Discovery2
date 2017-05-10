package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Collection;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ParticipateAdapter extends RecyclerView.Adapter<ParticipateViewHolder> implements View.OnClickListener  {
    private Context context;
    private List<Collection> list;
    private RequestManager manager;
    private int position;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int id);
    }

    private ParticipateAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(ParticipateAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public ParticipateAdapter(Context context, List<Collection> list) {
        this.context = context;
        this.list = list;
        manager = Glide.with(context);

    }
    @Override
    public ParticipateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_details, parent, false);
        ParticipateViewHolder holder = new ParticipateViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ParticipateViewHolder holder, int position) {
        this.position=position;
        final Collection collection=list.get(position);
        holder.btCollection.setVisibility(View.GONE);
        String url="http://"+collection.getLogo();
        manager.load(url).into(holder.commodityImage);//加载网络图片
        holder.tvTitle.setText(collection.getTitle());
        holder.tvSubtitle.setText("");
        holder.tvMoney.setText("");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
       mOnItemClickListener.onItemClick(view,position);

    }

}