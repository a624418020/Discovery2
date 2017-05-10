package milai.meishipintu.com.faxianlite.model.Retrofit;

import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.model.beans.Collection;
import milai.meishipintu.com.faxianlite.model.beans.Coupon;
import milai.meishipintu.com.faxianlite.model.beans.CouponResult;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.model.beans.WantItem;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    //根据Id获取新闻内容
    public Observable<Recommend> getRecomendInfo(int id) {
        return netService.getRecommendInfoByIdHttp(id).map(new MyResultFunc<Recommend>());
    }

    //获取活动信息
    public Observable<List<Red>> getActivityInformation(String news_id){
        Observable<HttpResult<List<Red>>> observable;
        observable=netService.getActivityInformationHttp(news_id);
        return observable.map(new MyResultFunc<List<Red>>());
    }

    //抢券
    public Observable<Coupon> getCouponInformation(String uniqid,String bundle,String mobile){
        return netService.getCouponInformationHttp(uniqid,bundle,mobile).map(new Func1<CouponResult,Coupon>() {
            @Override
            public Coupon call(CouponResult couponResult) {
                Log.i("couponResult",couponResult.toString());
                if(couponResult.getStatus()!=1){
                    throw new RuntimeException(couponResult.getInfo());
                }else {
                    return couponResult.getCoupon();
                }
            }
        });

    }
    //参与活动
    public Observable<List<Red>> participate(String uid,String activityid,String coupon_sn){
        return netService.participateHttp(uid,activityid,coupon_sn).map(new MyResultFunc<List<Red>>());
    }
    //获取活动列表
    public Observable<List<Collection>> getActivityList(String uid){
        return netService.getActivityListHttp(uid).map(new MyResultFunc<List<Collection>>());
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
    //type = 1 验证码登录， type=2 密码登录  type=3 微信登录
    public Observable<UserInfo> login(int type, String tel, @Nullable String vcode, @Nullable String password) {
        return netService.loginHttp(type, tel, vcode, password).map(new MyResultFunc<UserInfo>());
    }

    //修改密码

    //修改昵称或性别
    public Observable<UserInfo> updateUserInfo(String uid, @Nullable String name, @Nullable Integer sex) {
        return netService.updateUserInfoHttp(uid, name, sex).map(new MyResultFunc<UserInfo>());
    }

    //报存用户头像
    public Observable<UserInfo> addHeaderPic(File photeFile, String uid) {
        //将file类型转化为MultipartBody.part类型
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), photeFile);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("picture", "avator.jpg", photoRequestBody);
        //MultipartBody.Part uidPart = MultipartBody.Part.createFormData("uid", uid);
        RequestBody uidRequest = RequestBody.create(null, uid);
        return netService.addHeaderPicHttp(photo, uidRequest).map(new MyResultFunc<UserInfo>());
    }

    //添加收藏
    public Observable<Integer> addWant(String activityId, String uid) {
        return netService.addWantHttp(activityId, uid).map(new Func1<ResponseBody, Integer>() {
            @Override
            public Integer call(ResponseBody responseBody) {
                try {
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    if (1 == jsonObject.getInt("status")) {
                        return 1;
                    } else {
                        throw new RuntimeException(jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    throw new RuntimeException("添加收藏失败，请稍后重试");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("添加收藏失败，请稍后重试");

                }
            }
        });
    }

    //删除收藏
    public Observable<Integer> deleteWant(int activityId, String uid) {
        return netService.deletWantHttp(activityId, uid).map(new Func1<ResponseBody, Integer>() {
            @Override
            public Integer call(ResponseBody responseBody) {
                try {
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    if (1 == jsonObject.getInt("status")) {
                        return 1;
                    } else {
                        throw new RuntimeException(jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    throw new RuntimeException("取消收藏失败，请稍后重试");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("取消收藏失败，请稍后重试");
                }
            }
        });
    }

    //获取收藏列表
    public Observable<WantItem> getWantList(String uid) {
        return netService.getWantListHttp(uid).map(new MyResultFunc<List<WantItem>>()).flatMap(
                new Func1<List<WantItem>, Observable<WantItem>>() {
            @Override
            public Observable<WantItem> call(List<WantItem> wantItems) {
                return Observable.from(wantItems).filter(new Func1<WantItem, Boolean>() {
                    @Override
                    public Boolean call(WantItem wantItem) {
                        if (StringUtils.isNullOrEmpty(wantItem.getTitle())) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                });
            }
        });
    }
}
