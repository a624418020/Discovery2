package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.DateUtil;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.view.activity.DetailsActivity;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 功能介绍：
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_EMPTY = 1;
    private final int TYPE_NORMAL = 2;

    private List<Recommend> searchList;
    private Context mContext;
    private RequestManager manager;

    public SearchAdapter(List<Recommend> searchList, Context mContext) {
        this.searchList = searchList;
        this.mContext = mContext;
        manager = Glide.with(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_EMPTY) {
            return new EmptyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_empty, parent, false));
        } else {
            return new SearchViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            SearchViewHolder holder1 = (SearchViewHolder) holder;
            final Recommend recommend = searchList.get(position);
            manager.load(recommend.getLogo()).placeholder(R.drawable.default_samll_rec)
                    .error(R.drawable.default_samll_rec).into(holder1.ivLogo);
            holder1.tvTitle.setText(recommend.getTitle());
            holder1.tvTime.setText("发现 " + DateUtil.formart4(recommend.getTime()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Recommend", recommend);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && searchList.size()==0) {
            return TYPE_EMPTY;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        if (searchList.size() == 0) {
            return 1;
        } else {
            return searchList.size();
        }
    }
}
