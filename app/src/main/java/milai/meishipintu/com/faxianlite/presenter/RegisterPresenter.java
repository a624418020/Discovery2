package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.constract.RegisterContract;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 主要功能：
 */

public class RegisterPresenter implements RegisterContract.IPresenter {

    private RegisterContract.IView iView;
    private CompositeSubscription subscriptions;

    public RegisterPresenter(RegisterContract.IView iView) {
        this.iView = iView;
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }

    @Override
    public void getVCode(String tel) {

    }

    @Override
    public void register(String tel, String vCode, String password) {

    }

    @Override
    public void loginWeiChat() {

    }
}
