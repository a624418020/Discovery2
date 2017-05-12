package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.constract.ModifyPhoneContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/5/12.
 * <p>
 * 主要功能：
 */

public class ModifyPhonePresenter implements ModifyPhoneContract.IPresenter {

    private ModifyPhoneContract.IView IView;
    private NetApi netApi;
    private CompositeSubscription subscriptions;

    @Override
    public void getVerifyCode(String tel) {

    }

    @Override
    public void modifyPhone(String uid, String newTel, String verifyCode) {

    }

    @Override
    public void unsubscrib() {

    }
}
