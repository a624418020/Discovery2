package milai.meishipintu.com.faxianlite.presenter;

import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.view.viewinterface.AppView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class RecommendPresenter implements Presenter {

    private AppView viewInterface;
    private  List<Recommend> data;
    private NetApi netApi;

    public RecommendPresenter(AppView viewInterface) {
        this.viewInterface = viewInterface;
    }

    public void getdata() {
        data=new ArrayList<Recommend>();
        netApi = NetApi.getInstance();
        netApi.getrecommend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Recommend>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("调用失败",e+"");
                    }

                    @Override
                    public void onNext(List<Recommend> recommends) {
                        data.addAll(recommends) ;
                        Log.i("Success",data.size()+"");
                        viewInterface.getrecommenddata(data);
                    }
                });


    }

    @Override
    public void loadInstallApps() {

    }

    @Override
    public void launchApp(PackageInfo packageInfo) {

    }
}
