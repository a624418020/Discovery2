package milai.meishipintu.com.faxianlite.constract;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 功能介绍：
 */

public interface DiscoverContract {

    interface IPresenter extends BasicPresenter{
        void getrecommendData();
    }

    interface IView extends BasicView{
        void showRecommendData(List<Recommend> data);
    }
}
