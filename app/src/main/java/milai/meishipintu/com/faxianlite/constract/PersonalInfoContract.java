package milai.meishipintu.com.faxianlite.constract;

import java.io.File;

import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/5/7.
 * <p>
 * 功能介绍：
 */

public interface PersonalInfoContract {

    interface IPresenter extends BasicPresenter{

        void getPersonalInfo();

        void saveAvatar(File file, String uid);
    }

    interface IView extends BasicView{
        void onPersonalInfoGet(UserInfo userInfo);

        void refreshUI(UserInfo userInfo);
    }
}
