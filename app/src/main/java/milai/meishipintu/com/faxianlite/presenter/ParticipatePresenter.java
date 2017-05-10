package milai.meishipintu.com.faxianlite.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.constract.ParticipateContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Collection;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ParticipatePresenter implements ParticipateContract.IPresenter{

    private ParticipateContract.IView view;
    private List<Collection> collection;
    private List<Red> red;
    private NetApi netApi;
    private CompositeSubscription subscriptions;

    public ParticipatePresenter(ParticipateContract.IView iView){
        view = iView;
        subscriptions = new CompositeSubscription();

    }


    @Override
    public void unsubscrib() {

    }


    @Override
    public void getData(String uid) {
        collection=new ArrayList<>();
        netApi= NetApi.getInstance();
        subscriptions.add(netApi.getActivityList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Collection>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("a",e+"");

                    }

                    @Override
                    public void onNext(List<Collection> list) {
                        view.showData(list);

                    }
                })
        );

    }
    public void getCouponInfo(String news_id){
        red=new ArrayList<>();
        netApi=NetApi.getInstance();
        subscriptions.add(netApi.getActivityInformation(news_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Red>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("a",e+"");
                    }

                    @Override
                    public void onNext(List<Red> reds) {
                        Log.i("a","成功");
                        red.addAll(reds);
                        view.showCouponInfo(reds);
                    }
                })

        );


    }

}
