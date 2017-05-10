package milai.meishipintu.com.faxianlite.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;

/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 主要功能：
 */

public class WantedViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.commodity_image)
    ImageView commodityImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.bt_cancel)
    TextView btCancel;
    @BindView(R.id.iv_select)
    ImageView ivSelect;

    public WantedViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
