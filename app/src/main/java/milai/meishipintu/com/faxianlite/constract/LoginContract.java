package milai.meishipintu.com.faxianlite.constract;

import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 主要功能：
 */

public interface LoginContract {

    interface IPresenter extends BasicPresenter{

        void getVCode(String tel);

        void login(String tel, String vCode);

        void loginWeiChat();
    }

    interface IView extends BasicView{

        void onVCodeGet(String VCode);

        void onLoginSuccess(UserInfo userInfo);
    }
}
