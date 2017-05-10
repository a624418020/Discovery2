package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.view.activity.SearchActivityListener;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 功能介绍：
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private Context mContext;
    private List<String> historyData;
    private SearchActivityListener mListener;

    public HistoryAdapter(Context mContext, List<String> historyData) {
        this.mContext = mContext;
        this.historyData = historyData;
        this.mListener = (SearchActivityListener) mContext;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_record, parent, false);
        return new HistoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        final int positionNow = position;
        holder.tvName.setText(historyData.get(positionNow));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onHistoryClick(historyData.get(positionNow));
            }
        });
    }


    @Override
    public int getItemCount() {
        return historyData.size();
    }
}
