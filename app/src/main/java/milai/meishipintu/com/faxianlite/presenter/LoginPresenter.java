package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.constract.LoginContract;
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

public class LoginPresenter implements LoginContract.IPresenter {

    private LoginContract.IView iView;
    private NetApi netApi;
    private CompositeSubscription subscriptions;

    public LoginPresenter(LoginContract.IView iView) {
        this.iView = iView;
        netApi = NetApi.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void getVCode(String tel) {
        subscriptions.add(netApi.getVerifyCode(tel).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        iView.onVCodeGet(s);
                    }
                }));
    }

    @Override
    public void login(String tel, String vCode) {
        subscriptions.add(netApi.login(1,tel,vCode,null).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
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
                        iView.onLoginSuccess(userInfo);
                    }
                }));
    }

    @Override
    public void loginWeiChat() {

    }

    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
