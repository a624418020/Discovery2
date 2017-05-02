package milai.meishipintu.com.faxianlite.constract;

import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：
 */

public interface DetailContract {

    interface IPresenter extends BasicPresenter {
        //获取商品详情
        void getActivityInfo(int index);

        //添加收藏
        void addWant(int index);

    }

    interface IView extends BasicView {

        //显示商品详情
        void showActivity();
    }

}
