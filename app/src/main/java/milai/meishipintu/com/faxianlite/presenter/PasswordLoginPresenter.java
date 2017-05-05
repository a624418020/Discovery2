package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.Tool.Encoder;
import milai.meishipintu.com.faxianlite.constract.LoginContract;
import milai.meishipintu.com.faxianlite.constract.PasswordLoginContract;
import milai.meishipintu.com.faxianlite.model.PreferrenceHepler;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 功能介绍：
 */

public class PasswordLoginPresenter implements PasswordLoginContract.IPresenter {

    private PasswordLoginContract.IView iView;
    private NetApi netApi;
    private CompositeSubscription subscriptions;

    public PasswordLoginPresenter(PasswordLoginContract.IView iView) {
        this.iView = iView;
        netApi = NetApi.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void login(String tel, String password) {
        subscriptions.add(netApi.login(2,tel,null, Encoder.md5(password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        //保存用户数据
                        PreferrenceHepler.saveUser(userInfo);
                        DiscoverApplication.setUser(userInfo);
                        iView.onLoginSuccess();
                    }
                }));
    }

    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
