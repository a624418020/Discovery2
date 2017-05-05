package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.view.activity.DetailsActivity;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class DiscoverSubtitleAdapter extends RecyclerView.Adapter< DiscoverSubtitleViewHolder> implements View.OnClickListener  {
    private Context context;
    private List<Recommend> list;
    private Picasso picasso;
    private int position;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String id);
    }

    private DiscoverSubtitleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(DiscoverSubtitleAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public DiscoverSubtitleAdapter(Context context,List<Recommend> list) {
        this.context = context;
        this.list = list;
        picasso = Picasso.with(context);

    }
    @Override
    public  DiscoverSubtitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
         DiscoverSubtitleViewHolder holder = new  DiscoverSubtitleViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiscoverSubtitleViewHolder holder, final int position) {
        this.position=position;
        final Recommend recommend = list.get(position);
        Glide.with(context).
                load(recommend.getLogo()).
                asBitmap(). //强制处理为bitmap
                into(holder.ivimage);//显示到目标View中
        Log.i("image",recommend.getLogo());
        holder.subtitle.setText(recommend.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //Intent传递参数
                intent.putExtra("id", position);
                intent.setClass(context, DetailsActivity.class);
                Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

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