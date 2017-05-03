package milai.meishipintu.com.faxianlite.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.MyRecyclerView;

/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 主要功能：
 */

public class WantedViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_delete)
    public TextView btn_Delete;
    @BindView(R.id.text)
    public TextView textView;
    @BindView(R.id.layout_content)
    public ViewGroup layout_content;

    public WantedViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
