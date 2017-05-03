package milai.meishipintu.com.faxianlite.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;

/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 主要功能：
 */

public class DiscoverViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_mainimaget)
    ImageView ivMainimaget;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.designer)
    TextView designer;
    @BindView(R.id.name)
    TextView name;

    public DiscoverViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
