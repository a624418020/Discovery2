package milai.meishipintu.com.faxianlite.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 功能介绍：
 */

public class SearchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_logo)
    public ImageView ivLogo;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_time)
    public TextView tvTime;

    public SearchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
