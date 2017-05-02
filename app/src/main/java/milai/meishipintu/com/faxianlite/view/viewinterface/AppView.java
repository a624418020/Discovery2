package milai.meishipintu.com.faxianlite.view.viewinterface;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by pc on 2016/4/28.
 */
public interface AppView extends BasicView {
    //发现页面获取页面数据
    void getrecommenddata(List<Recommend> data);
}
