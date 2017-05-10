package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Recommend;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 功能介绍：
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<Recommend> searchList;
    private Context mContext;
    private RequestManager manager;

    public SearchAdapter(List<Recommend> searchList, Context mContext) {
        this.searchList = searchList;
        this.mContext = mContext;
        manager = Glide.with(mContext);
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
