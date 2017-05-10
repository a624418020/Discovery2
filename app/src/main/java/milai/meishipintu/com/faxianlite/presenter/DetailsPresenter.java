package milai.meishipintu.com.faxianlite.presenter;

import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.constract.DetailContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class DetailsPresenter implements DetailContract.IPresenter {

    private DetailContract.IView detailsViewInterface;
    private NetApi netApi;
    private CompositeSubscription subscriptions;

    public DetailsPresenter(DetailContract.IView detailsViewInterface){
        this.detailsViewInterface=detailsViewInterface;
        netApi = NetApi.getInstance();
        subscriptions = new CompositeSubscription();
    }

    //from DetailContract.IPresenter
    @Override
    public void participate() {

    }

    //from DetailContract.IPresenter
    @Override
    public void addWant(String uid, String activityId) {
        subscriptions.add(netApi.addWant(activityId,uid).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        detailsViewInterface.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer == 1) {
                            detailsViewInterface.onWantSuccess();
                        } else {
                            detailsViewInterface.showError("添加收藏失败，请稍后重试");
                        }
                    }
                }));
    }

    //from DetailContract.IPresenter
    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
