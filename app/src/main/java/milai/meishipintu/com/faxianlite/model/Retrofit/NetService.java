package milai.meishipintu.com.faxianlite.model.Retrofit;

import android.support.annotation.Nullable;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Collection;
import milai.meishipintu.com.faxianlite.model.beans.CouponResult;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.model.beans.WantItem;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public interface NetService {

    //获取验证码
    @FormUrlEncoded
    @POST("/Api/Api/getVerifyCodeByMobile")
    Observable<HttpResult<String>> getVerifyCodeHttp(@Field("mobile") String tel);

    //注册
    @FormUrlEncoded
    @POST("/Api/Api/regist")
    Observable<HttpResult<UserInfo>> registerHttp(@Field("tel") String tel, @Field("verify") String vCode
            , @Field("password") String password);

    //验证码登录
    @FormUrlEncoded
    @POST("/Api/Api/login")
    Observable<HttpResult<UserInfo>> loginHttp(@Field("type") int type, @Field("tel") String tel
            , @Nullable @Field("verify") String vcode, @Nullable @Field("password") String password);

    //获取首页内容
    @FormUrlEncoded
    @POST("/Api/Api/applet_news")
    Observable<HttpResult<List<Recommend>>> getMainInfoListHttp(@Nullable @Field("number") String number);

    //根据ID获取新闻信息
    @FormUrlEncoded
    @POST("/Api/Api/getNewsDetail")
    Observable<HttpResult<Recommend>> getRecommendInfoByIdHttp(@Field("id") int id);

    //获取活动信息
    @FormUrlEncoded
    @POST("https://a.milaipay.com/mspt/Applet/getActivityByNewsId")
    Observable<HttpResult<List<Red>>> getActivityInformationHttp(@Nullable @Field("news_id") String news_id);

    //抢红包
    @FormUrlEncoded
    @POST("https://a.milaipay.com/wap/coupon/doreceive")
    Observable<CouponResult> getCouponInformationHttp(@Nullable @Field("uniqid") String uniqid,
                                                      @Nullable @Field("bundle") String bundle,
                                                      @Nullable @Field("mobile") String mobile);


    //参与活动
    @FormUrlEncoded
    @POST("/Api/Api/addActivity")
    Observable<HttpResult<List<Red>>> participateHttp(@Nullable @Field("uid") String uid,
                                                      @Nullable @Field("activityid") String activityid,
                                                      @Nullable @Field("coupon_sn") String coupon_sn);

    //获取活动列表
    @FormUrlEncoded
    @POST("/Api/Api/getUserActivityList")
    Observable<HttpResult<List<Collection>>> getActivityListHttp(@Nullable @Field("uid") String uid);

    //修改密码

    //修改昵称/性别
    @FormUrlEncoded
    @POST("/Api/Api/updateUserInfo")
    Observable<HttpResult<UserInfo>> updateUserInfoHttp(@Field("uid") String uid, @Nullable @Field("name") String name
            , @Nullable @Field("sex") Integer sex);

    //保存用户头像
    @Multipart
    @POST("/Api/Api/upload")
    Observable<HttpResult<UserInfo>> addHeaderPicHttp(@Part MultipartBody.Part file1, @Part("uid") RequestBody uid);

    //添加收藏
    @FormUrlEncoded
    @POST("/Api/Api/doCollection")
    Observable<ResponseBody> addWantHttp(@Field("activityid") String activityId, @Field("uid") String uid);

    //删除收藏
    @FormUrlEncoded
    @POST("/Api/Api/deleteCollection")
    Observable<ResponseBody> deletWantHttp(@Field("activityid") int activityId, @Field("uid") String uid);

    //获取收藏列表
    @FormUrlEncoded
    @POST("/Api/Api/getUserCollectionList")
    Observable<HttpResult<List<WantItem>>> getWantListHttp(@Field("uid") String uid);

    //搜索
    @FormUrlEncoded
    @POST("/Api/Api/applet_search")
    Observable<HttpResult<List<Recommend>>> searchHttp(@Field("content") String content);
}
