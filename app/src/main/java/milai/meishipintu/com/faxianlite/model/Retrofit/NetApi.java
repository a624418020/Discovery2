package milai.meishipintu.com.faxianlite.model.Retrofit;

import android.support.annotation.Nullable;

import java.util.List;

import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
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
                .baseUrl(Constant.BASE_URL)
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

    //获取首页内容列表
    public Observable<List<Recommend>> getMainInfoList(String number) {
        Observable<HttpResult<List<Recommend>>> observable;
        if ("0".equals(number)) {
            observable = netService.getMainInfoListHttp(null);
        } else {
            observable = netService.getMainInfoListHttp(number);
        }
        return observable.map(new MyResultFunc<List<Recommend>>());
    }

    //获取验证码
    public Observable<String> getVerifyCode(String tel) {
        return netService.getVerifyCodeHttp(tel).map(new MyResultFunc<String>());
    }

    //注册
    public Observable<UserInfo> register(String tel,String vcode, String password){
        return netService.registerHttp(tel,vcode,password).map(new MyResultFunc<UserInfo>());
    }

    //登录
    public Observable<UserInfo> login(int type, String tel, @Nullable String vcode, @Nullable String password) {
        return netService.loginHttp(type, tel, vcode, password).map(new MyResultFunc<UserInfo>());
    }
}
