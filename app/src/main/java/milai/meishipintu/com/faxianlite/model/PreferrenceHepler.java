package milai.meishipintu.com.faxianlite.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;

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

    public static void saveUser(UserInfo userInfo) {
        SharedPreferences.Editor editor = getSharePreference().edit();
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(userInfo);
            // 将字节流编码成base64的字符窜
            String oAuth_Base64 = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            editor.putString("user", oAuth_Base64);
            editor.apply();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static UserInfo getUser() {
        UserInfo userInfo = null;
        SharedPreferences sharePreference = getSharePreference();
        String productBase64 = sharePreference.getString("user", "");
        if (!StringUtils.isNullOrEmpty(productBase64)) {
            //读取字节
            byte[] base64 = Base64.decode(productBase64,Base64.DEFAULT);
            //封装到字节流
            ByteArrayInputStream bais = new ByteArrayInputStream(base64);
            try {
                //再次封装
                ObjectInputStream bis = new ObjectInputStream(bais);
                try {
                    //读取对象
                    userInfo = (UserInfo) bis.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                bis.close();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return userInfo;
    }

}
