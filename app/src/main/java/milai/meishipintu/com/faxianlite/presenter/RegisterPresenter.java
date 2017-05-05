package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.constract.RegisterContract;
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
 * 主要功能：
 */

public class RegisterPresenter implements RegisterContract.IPresenter {

    private RegisterContract.IView iView;
    private CompositeSubscription subscriptions;
    private NetApi netApi;

    public RegisterPresenter(RegisterContract.IView iView) {
        this.iView = iView;
        subscriptions = new CompositeSubscription();
        netApi = NetApi.getInstance();
    }

    @Override
    public void getVCode(String tel) {
        subscriptions.add(netApi.getVerifyCode(tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
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
    public void register(String tel, String vCode, String password) {
        subscriptions.add(netApi.register(tel,vCode,password)
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
                        iView.onRegisterSuccess();
                    }
                }));
    }

    @Override
    public void registerWeiChat() {
        //TODO 微信登陸

    }

    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
