package milai.meishipintu.com.faxianlite.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import milai.meishipintu.com.faxianlite.R;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 功能介绍：
 */

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;

    public HistoryViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_record);
    }
}
