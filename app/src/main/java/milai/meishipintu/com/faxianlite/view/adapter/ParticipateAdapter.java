package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Order;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ParticipateAdapter extends RecyclerView.Adapter<ParticipateViewHolder> implements View.OnClickListener  {
    private Context context;
    private List<Order> list;
    private Picasso picasso;
    private int position;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String id);
    }

    private RecommendAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(RecommendAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public ParticipateAdapter(Context context, List<Order> list) {
        this.context = context;
        this.list = list;
        picasso = Picasso.with(context);

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
        final Order order=list.get(position);
        holder.btCollection.setVisibility(View.GONE);
        picasso.load(order.getCommodity_image()).into(holder.commodityImage);//加载网络图片
        holder.tvTitle.setText(order.getCommodity_title());
        holder.tvSubtitle.setText(order.getCommodity_subtitle());
        holder.tvMoney.setText(order.getCommodity_value());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context,  position+"", Toast.LENGTH_SHORT).show();
    }

}