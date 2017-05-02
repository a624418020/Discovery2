package milai.meishipintu.com.faxianlite.constract;

import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：
 */

public interface OrderContract {

    interface IPresenter extends BasicPresenter{

        //获取活动信息
        void getCouponInfo(int id);

        //参与活动
        void paticipate(String name, String number, int id);
    }

    interface IView extends BasicView{

        void showCouponInfo();

        void onPaticipateSucess();
    }
}
