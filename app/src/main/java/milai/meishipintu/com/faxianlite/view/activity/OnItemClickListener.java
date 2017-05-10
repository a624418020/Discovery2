package milai.meishipintu.com.faxianlite.view.activity;

import milai.meishipintu.com.faxianlite.model.beans.WantItem;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 主要功能：
 */

public interface OnItemClickListener{

    void onItemClick(WantItem wantItem);

    void onItemRemove(int position);
}
