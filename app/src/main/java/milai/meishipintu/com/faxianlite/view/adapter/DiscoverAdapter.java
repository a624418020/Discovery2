package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.RecommendPackage;
import milai.meishipintu.com.faxianlite.view.activity.DetailsActivity;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverViewHolder> {

    private List<RecommendPackage> data = new ArrayList<>();

    private List<String> subtitle=new ArrayList<>();
    private Context context;
    private DiscoverSubtitleAdapter discoverSubtitleAdapter;

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
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        discoverSubtitleAdapter=new DiscoverSubtitleAdapter(context,smallRecommends);
        recyclerView.setAdapter(discoverSubtitleAdapter);
        Log.d("adapter", "url:" + headRecommend.getLogo());
        Glide.with(context).
                load(headRecommend.getLogo()).
                into(holder.ivMainimaget);//显示到目标View中
        Log.i("aa",headRecommend.getLogo());
        holder.itemView.setTag(headRecommend.getId());
        holder.title.setText(headRecommend.getTitle());
        holder.ivMainimaget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //Intent传递参数
                intent.putExtra("id", position);
                intent.setClass(context, DetailsActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();


    }

}
