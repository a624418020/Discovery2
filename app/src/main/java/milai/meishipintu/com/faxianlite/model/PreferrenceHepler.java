package milai.meishipintu.com.faxianlite.model;

import android.content.Context;
import android.content.SharedPreferences;

import milai.meishipintu.com.faxianlite.DiscoverApplication;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：
 */

public class PreferrenceHepler {

    public static String TAG = "FaXianLite-PreferenceHelper";

    private static SharedPreferences getSharePreference() {
        return DiscoverApplication.getInstance().getSharedPreferences(DiscoverApplication.class
                        .getPackage().getName(), Context.MODE_PRIVATE);
    }

    public static String getUid() {
        SharedPreferences sharePreference = getSharePreference();
        return sharePreference.getString("uid", null);
    }

    public static void saveUid(String uid){
        SharedPreferences.Editor editor = getSharePreference().edit();
        editor.putString("uid", uid);
        editor.apply();
    }


}
