package milai.meishipintu.com.faxianlite.presenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import milai.meishipintu.com.faxianlite.constract.DiscoverContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.RecommendPackage;
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
    public void getrecommendData(String number) {
        netApi = NetApi.getInstance();
        subscriptions.add(netApi.getMainInfoList(number)
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
                        String number = recommends.get(0).getNumber();
                        //初始化三个对象
                        List<RecommendPackage> packages = new ArrayList<>();
                        RecommendPackage recommendPackage = new RecommendPackage();
                        List<Recommend> smallRecommends = new ArrayList<>();
                        for (Recommend recommend : recommends) {
                            if (!recommend.getNumber().equals(number)) {
                                //下一期
                                //先封装完上一期的对象
                                Collections.sort(smallRecommends);
                                recommendPackage.setSmallRecommends(smallRecommends);
                                packages.add(recommendPackage);
                                //重建对象
                                recommendPackage = new RecommendPackage();
                                smallRecommends = new ArrayList<>();
                                //刷新
                                number = recommend.getNumber();
                            }
                            if (1 == recommend.getIs_main()) {
                                //头图
                                recommendPackage.setHeadRecommend(recommend);
                            } else {
                                smallRecommends.add(recommend);
                            }
                        }
                        Collections.sort(smallRecommends);
                        recommendPackage.setSmallRecommends(smallRecommends);
                        packages.add(recommendPackage);
                        iView.showRecommendData(packages);
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
