package milai.meishipintu.com.faxianlite;

import android.app.Application;

import com.tencent.bugly.Bugly;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：全局类
 */

public class DiscoverApplication extends Application {

    private static DiscoverApplication instance;

    public static DiscoverApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化Bugly
        Bugly.init(getApplicationContext(), "a50e729142", true);

    }
}
