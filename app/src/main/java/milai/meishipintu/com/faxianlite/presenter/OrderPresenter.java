package milai.meishipintu.com.faxianlite.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.constract.OrderContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Coupon;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class OrderPresenter implements OrderContract.IPresenter {
    private OrderContract.IView orderViewInterface;
    private List<Red> list;
    private Red luckymoney;
    private CompositeSubscription subscriptions;
    private NetApi netApi;
    private String onerror;

    public OrderPresenter(OrderContract.IView orderViewInterface){
        this.orderViewInterface=orderViewInterface;
        subscriptions = new CompositeSubscription();
    }

    public void getCouponInfo(String news_id){
        list=new ArrayList<>();
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
                        list.addAll(reds);
                        orderViewInterface.showCouponInfo(list);
                    }
                })

        );


    }


    //from OrderContract.IPresenter
    @Override
    public void paticipate(String uniqid, String bundleid, String mobile) {
        netApi=NetApi.getInstance();
        subscriptions.add(netApi.getCouponInformation(uniqid,bundleid,mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Coupon>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onerror=e.toString();
                        orderViewInterface.onPaticipateSucess(null,onerror);
                        Log.i("bb",onerror+"");
                    }

                    @Override
                    public void onNext(Coupon coupon) {
                        Log.i("bb","成功");
                        Collection(coupon);
                    }
                })
        );
    }
    public void Collection(final Coupon coupon){
        netApi=NetApi.getInstance();
        subscriptions.add(netApi.participate(DiscoverApplication.getUser().getUid(),"153",coupon.getCoupon_sn())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Red>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError",e.toString());
                        Log.i("getUid",DiscoverApplication.getUser().getUid());

                    }

                    @Override
                    public void onNext(List<Red> list) {
                        Log.i("Uid",DiscoverApplication.getUser().getUid());
                        orderViewInterface.onPaticipateSucess(coupon,null);

                    }
                })
        );
    }

    //from OrderContract.IPresenter
    @Override
    public void unsubscrib() {

    }
}
