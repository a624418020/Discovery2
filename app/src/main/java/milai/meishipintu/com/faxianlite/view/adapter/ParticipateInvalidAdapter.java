package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Order;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ParticipateInvalidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener  {
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

    public ParticipateInvalidAdapter(Context context, List<Order> list) {
        this.context = context;
        this.list = list;
        picasso = Picasso.with(context);

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_details, parent, false);
        ParticipateInvalidAdapter.MyViewHolder holder = new ParticipateInvalidAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.position=position;
        final Order order=list.get(position);
        final ParticipateInvalidAdapter.MyViewHolder MyViewHolder= (ParticipateInvalidAdapter.MyViewHolder) holder;
        MyViewHolder.btCollection.setVisibility(View.GONE);
        picasso.load(order.getCommodity_image()).into(MyViewHolder.commodityImage);//加载网络图片
        MyViewHolder.tvTitle.setText(order.getCommodity_title());
        MyViewHolder.tvSubtitle.setText(order.getCommodity_subtitle());
        MyViewHolder.tvMoney.setText(order.getCommodity_value());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context,  position+"", Toast.LENGTH_SHORT).show();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.commodity_image)
        ImageView commodityImage;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_subtitle)
        TextView tvSubtitle;
        @Bind(R.id.tv_money)
        TextView tvMoney;
        @Bind(R.id.bt_collection)
        RelativeLayout btCollection;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}