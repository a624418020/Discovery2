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

public class DiscoverSubtitleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image)
    ImageView ivimage;
    @BindView(R.id.tv_subtitle)
    TextView subtitle;

    public DiscoverSubtitleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
