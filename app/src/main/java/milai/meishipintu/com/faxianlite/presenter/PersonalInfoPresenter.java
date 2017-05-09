package milai.meishipintu.com.faxianlite.presenter;

import android.content.Intent;
import android.util.Log;

import java.io.File;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.constract.PersonalInfoContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.view.activity.SexActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/5/7.
 * <p>
 * 功能介绍：
 */

public class PersonalInfoPresenter implements PersonalInfoContract.IPresenter {

    private PersonalInfoContract.IView iView;
    private NetApi netApi;
    private CompositeSubscription subscriptions;

    public PersonalInfoPresenter(PersonalInfoContract.IView iView) {
        this.iView = iView;
        this.netApi = NetApi.getInstance();
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void getPersonalInfo() {
        iView.onPersonalInfoGet(DiscoverApplication.getUser());
    }

    @Override
    public void saveAvatar(File file, String uid) {
        subscriptions.add(netApi.addHeaderPic(file,uid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.showError("保存头像失败，请稍后重试");
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        iView.refreshUI(userInfo);
                    }
                }));
    }

    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
