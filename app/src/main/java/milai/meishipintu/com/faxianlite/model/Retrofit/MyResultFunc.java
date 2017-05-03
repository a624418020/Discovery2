package milai.meishipintu.com.faxianlite.model.Retrofit;

import android.util.Log;

import rx.functions.Func1;


/**
 * Created by Administrator on 2017/5/3.
 * <p>
 * 功能介绍：
 */

public class MyResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getStatus() != 1) {
            throw new RuntimeException(httpResult.getMsg());
        }
        Log.i("test", httpResult.getData().toString());
        return httpResult.getData();
    }
}
