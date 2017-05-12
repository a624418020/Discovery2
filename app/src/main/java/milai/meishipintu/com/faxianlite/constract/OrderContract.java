package milai.meishipintu.com.faxianlite.constract;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Coupon;
import milai.meishipintu.com.faxianlite.model.beans.Red;
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
        void getCouponInfo(String id);

        void paticipate_uniqid(String uniqid, String bundleid, String mobile);
        //参与活动
        void paticipate(String uniqid, String bundleid, String mobile);

    }

    interface IView extends BasicView{

        void showCouponInfo(List<Red> list);

        void onPaticipateSucess(Coupon coupon,String onerror);
    }
}
