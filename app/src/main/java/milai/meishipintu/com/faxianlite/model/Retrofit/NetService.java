package milai.meishipintu.com.faxianlite.model.Retrofit;

import android.support.annotation.Nullable;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.CouponResult;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
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

    //获取活动信息
    @FormUrlEncoded
    @POST("https://a.milaipay.com/mspt/Applet/getActivityByNewsId")
    Observable<HttpResult<List<Red>>> getActivityInformationHttp(@Nullable @Field("news_id") String news_id);
    //获取活动信息
    @FormUrlEncoded
    @POST("https://a.milaipay.com/wap/coupon/doreceive")
    Observable<CouponResult> getCouponInformationHttp(@Nullable @Field("uniqid") String uniqid,
                                                              @Nullable @Field("bundle") String bundle,
                                                              @Nullable @Field("mobile") String mobile);
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

}
