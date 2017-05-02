package milai.meishipintu.com.faxianlite.model.Retrofit;

import android.util.Log;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class NetApi {
    private static NetApi netApi;
    private NetService netService;
    private Retrofit retrofit = null;
    private NetApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a.milaipay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        netService = retrofit.create(NetService.class);
    }

    public static NetApi getInstance() {
        synchronized (NetApi.class) {
            if (netApi == null) {
                netApi = new NetApi();
            }
            return netApi;
        }
    }

    public Observable<List<Recommend>> getrecommend() {
//        Log.i("返回的数据",netService.getRecommendHttp()+"");
        return netService.getRecommendHttp().map(new MyResultFunc<List<Recommend>>());
    }

    class MyResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getStatus() != 1) {
                throw new RuntimeException(httpResult.getMsg());
            }
            Log.i("test", httpResult.getData().toString());
            return httpResult.getData();
        }
    }
}
