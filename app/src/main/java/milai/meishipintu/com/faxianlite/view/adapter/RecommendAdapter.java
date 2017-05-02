package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<Recommend> data;
    private Context context;
    private Picasso picasso;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,String id);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public RecommendAdapter(Context context, List<Recommend> list) {
        this.data = list;
        this.context = context;
        picasso = Picasso.with(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recomment, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("data1",data.size()+"");
        final Recommend recommend=data.get(position);
        final MyViewHolder MyViewHolder= (RecommendAdapter.MyViewHolder) holder;
        picasso.load(recommend.getLogo()).into(MyViewHolder.ivMainimaget);//加载网络图片
        MyViewHolder.itemView.setTag(recommend.getId());
        MyViewHolder.title.setText(data.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view,(String)view.getTag());
        }
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_mainimaget)
        ImageView ivMainimaget;
        @Bind(R.id.rl)
        RelativeLayout rl;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.designer)
        TextView designer;
        @Bind(R.id.name)
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
