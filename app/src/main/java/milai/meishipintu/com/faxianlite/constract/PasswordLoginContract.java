package milai.meishipintu.com.faxianlite.constract;

import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 主要功能：
 */

public interface PasswordLoginContract {

    interface IPresenter extends BasicPresenter{

        void login(String tel, String password);

    }

    interface IView extends BasicView{

        void onLoginSuccess();
    }
}
