package milai.meishipintu.com.faxianlite.constract;

import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/7.
 * <p>
 * 功能介绍：
 */

public interface PersonalInfoContract {

    interface IPresenter extends BasicPresenter{

        void getPersonalInfo();
    }

    interface IView extends BasicView{
        void onPersonalInfoGet(UserInfo userInfo);
    }
}
