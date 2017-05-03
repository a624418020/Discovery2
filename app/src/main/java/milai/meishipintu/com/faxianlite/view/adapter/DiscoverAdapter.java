package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.view.activity.DetailsActivity;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverViewHolder> {

    private List<Recommend> data = new ArrayList<>();
    private Context context;
    private Picasso picasso;

    public DiscoverAdapter(Context context, List<Recommend> list) {
        this.data = list;
        this.context = context;
        picasso = Picasso.with(context);
    }


    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recomment, parent, false);
        DiscoverViewHolder holder = new DiscoverViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, final int position) {

        final Recommend recommend = data.get(position);
        picasso.load(recommend.getLogo()).into(holder.ivMainimaget);//加载网络图片
        holder.itemView.setTag(recommend.getId());
        holder.title.setText(data.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //Intent传递参数
                intent.putExtra("id", position);
                intent.setClass(context, DetailsActivity.class);
                context.startActivity(intent);
                Toast.makeText(v.getContext(),  position+"" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();


    }

}
