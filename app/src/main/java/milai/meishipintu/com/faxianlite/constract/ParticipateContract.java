package milai.meishipintu.com.faxianlite.constract;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Collection;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 功能介绍：
 */

public interface ParticipateContract {

    interface IPresenter extends BasicPresenter {
        void getData(String uid);
        void getCouponInfo(String news_id);
    }

    interface IView extends BasicView {
        void showData(List<Collection> list);
        void showCouponInfo(List<Red> red);
    }

}
