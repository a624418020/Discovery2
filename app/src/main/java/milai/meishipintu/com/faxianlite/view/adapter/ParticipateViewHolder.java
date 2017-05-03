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

public class ParticipateViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.commodity_image)
    ImageView commodityImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.bt_collection)
    RelativeLayout btCollection;

    public ParticipateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
