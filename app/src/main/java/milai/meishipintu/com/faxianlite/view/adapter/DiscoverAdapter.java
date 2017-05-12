package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.BetterRecyclerView;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.RecommendPackage;
import milai.meishipintu.com.faxianlite.view.activity.DetailsActivity;


public class DiscoverAdapter extends BetterRecyclerView.Adapter<DiscoverViewHolder> {

    private List<RecommendPackage> data = new ArrayList<>();

    private List<String> subtitle=new ArrayList<>();
    private Context context;
    private DiscoverSubtitleAdapter discoverSubtitleAdapter;
    private int position;

    public DiscoverAdapter(Context context, List<RecommendPackage> list) {
        this.data = list;
        this.context = context;
    }


    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recomment, parent, false);
        DiscoverViewHolder holder = new DiscoverViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, final int position) {

        final Recommend headRecommend = data.get(position).getHeadRecommend();
        final List<Recommend> smallRecommends=data.get(position).getSmallRecommends();
        final RecyclerView recyclerView=holder.rvimages;
        this.position=position;
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        discoverSubtitleAdapter=new DiscoverSubtitleAdapter(context,smallRecommends);
        recyclerView.setAdapter(discoverSubtitleAdapter);
        Log.d("adapter", "url:" + headRecommend.getLogo());
        Glide.with(context).
                load(headRecommend.getLogo()).
                placeholder(R.drawable.default_big_square).
                error(R.drawable.default_big_square).
                into(holder.ivMainimaget);//显示到目标View中
        Log.i("aa",headRecommend.getLogo());
        holder.itemView.setTag(headRecommend.getId());
        holder.title.setText(headRecommend.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Recommend",  headRecommend);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();

    }
    public int getposition() {
        return position;

    }

}
