package milai.meishipintu.com.faxianlite.presenter;

import java.util.List;

import milai.meishipintu.com.faxianlite.constract.SearchContract;
import milai.meishipintu.com.faxianlite.model.PreferrenceHepler;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 主要功能：
 */

public class SearchPresenter implements SearchContract.IPresenter {

    private SearchContract.IView iView;
    private NetApi netApi;
    private CompositeSubscription subscriptions;

    public SearchPresenter(SearchContract.IView iView) {
        this.iView = iView;
        netApi = NetApi.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void loadHistory() {
        List<String> strings = PreferrenceHepler.loadSearchHistory();
//        iView.onHistoryGet(strings);
    }

    @Override
    public void clearHistory() {
        PreferrenceHepler.clearHistory();
//        iView.onHistoryGet(new ArrayList<String>());
    }

    @Override
    public void doSearch(String content) {
        subscriptions.add(netApi.search(content).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Recommend>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iView.showError("搜索失败，请稍后重试");
                    }

                    @Override
                    public void onNext(List<Recommend> recommends) {
                        iView.onSearchInfoGet(recommends);
                    }
                }));
    }

    @Override
    public void addToHistory(List<String> history, String content) {
        history.remove(content);
        history.add(0, content);
    }

    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
