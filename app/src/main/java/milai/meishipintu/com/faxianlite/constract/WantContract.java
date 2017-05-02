package milai.meishipintu.com.faxianlite.constract;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：
 */

public interface WantContract {

    interface IPresenter extends BasicPresenter {

        void getWantList();

        void deletWant(int[] deletNum);
    }

    interface IView extends BasicView{

        void showWantList(List<Order> orderList);

        void reFreshList();
    }
}
