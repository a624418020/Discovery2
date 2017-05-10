package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.constract.SearchContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
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
    public void getHistory() {

    }

    @Override
    public void clearHistory() {

    }

    @Override
    public void doSearch(String content) {

    }

    @Override
    public void unsubscrib() {
        subscriptions.clear();
    }
}
