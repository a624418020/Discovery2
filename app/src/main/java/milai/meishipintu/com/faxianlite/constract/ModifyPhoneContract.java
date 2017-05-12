package milai.meishipintu.com.faxianlite.constract;

import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/12.
 * <p>
 * 主要功能：
 */

public interface ModifyPhoneContract {

    interface IPresenter extends BasicPresenter {

        void getVerifyCode(String tel);

        void modifyPhone(String uid, String newTel, String verifyCode);
    }

    interface IView extends BasicView {

        void onVerifyCodeGet(String verifyCode);

        void onModifyPhoneSucess();
    }

}
