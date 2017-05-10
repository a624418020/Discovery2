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

        //添加收藏
        void addWant(String uid,String activityId);

        //参加活动
        void participate();
    }

    interface IView extends BasicView {

        //收藏成功
        void onWantSuccess();
    }

}
