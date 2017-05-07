package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.constract.PersonalInfoContract;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
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
    public void unsubscrib() {
        subscriptions.clear();
    }
}
