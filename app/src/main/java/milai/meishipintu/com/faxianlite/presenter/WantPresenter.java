package milai.meishipintu.com.faxianlite.presenter;

import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.constract.WantContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.WantItem;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class WantPresenter implements WantContract.IPresenter {

    private WantContract.IView iView;
    private CompositeSubscription subscriptions;
    private NetApi netApi;

    public WantPresenter (WantContract.IView wantViewInterface){
        iView = wantViewInterface;
        netApi = NetApi.getInstance();
        subscriptions = new CompositeSubscription();
    }


    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }

    @Override
    public void getWantList(String uid) {
        final List<WantItem> wantItemList = new ArrayList<>();
        subscriptions.add(netApi.getWantList(uid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WantItem>() {
                    @Override
                    public void onCompleted() {
                        iView.showWantList(wantItemList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.showError("获取收藏列表失败，请稍后重试");
                    }

                    @Override
                    public void onNext(WantItem wantItem) {
                        wantItemList.add(wantItem);
                    }
                }));
    }

    @Override
    public void deletWant(String uid, int acitivityId) {
        subscriptions.add(netApi.deleteWant(acitivityId,uid).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.showError(e.getMessage());
                        iView.onDeletFaild();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        iView.onDeletSuccess();
                    }
                }));
    }

    @Override
    public void getRecomendInfo(int id) {
        subscriptions.add(netApi.getRecomendInfo(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Recommend>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iView.showError("获取新闻信息失败，请稍后重试");
                    }

                    @Override
                    public void onNext(Recommend recommend) {
                        iView.onRecommedInfoGet(recommend);
                    }
                }));
    }
}
