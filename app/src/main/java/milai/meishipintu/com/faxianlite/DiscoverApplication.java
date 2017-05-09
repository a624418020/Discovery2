package milai.meishipintu.com.faxianlite;

import android.app.Application;

import com.tencent.bugly.Bugly;

import milai.meishipintu.com.faxianlite.model.PreferrenceHepler;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：app全局类
 */

public class DiscoverApplication extends Application {

    private static DiscoverApplication instance;
    private static UserInfo user;

    public static DiscoverApplication getInstance(){
        return instance;
    }

    public static UserInfo getUser() {
        return user;
    }

    public static void setUser(UserInfo userInfo) {
        user = userInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化Bugly
//        Bugly.init(getApplicationContext(), "a50e729142", true);
        user = PreferrenceHepler.getUser();
    }
}
