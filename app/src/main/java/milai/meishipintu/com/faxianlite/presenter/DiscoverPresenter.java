package milai.meishipintu.com.faxianlite.presenter;

import java.util.List;

import milai.meishipintu.com.faxianlite.constract.DiscoverContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class DiscoverPresenter implements DiscoverContract.IPresenter {

    private DiscoverContract.IView iView;
    private CompositeSubscription subscriptions;
    private NetApi netApi;

    public DiscoverPresenter(DiscoverContract.IView view) {
        iView = view;
        subscriptions = new CompositeSubscription();
    }


    @Override
    public void getrecommendData() {
        netApi = NetApi.getInstance();
        subscriptions.add(netApi.getrecommend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Recommend>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Recommend> recommends) {
                        iView.showRecommendData(recommends);
                    }
                }
            )
        );
    }


    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
