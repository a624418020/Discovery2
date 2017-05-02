package milai.meishipintu.com.faxianlite.constract;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 功能介绍：
 */

public interface ParticipateContract {

    interface IPresenter extends BasicPresenter {
        void getData(int type);
    }

    interface IView extends BasicView {
        void showData(List<Order> orderList);
    }

}
