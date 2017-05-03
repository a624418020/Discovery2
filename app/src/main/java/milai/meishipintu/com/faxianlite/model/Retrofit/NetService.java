package milai.meishipintu.com.faxianlite.model.Retrofit;

import android.support.v4.media.session.PlaybackStateCompat;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public interface NetService {

    //获取推举页面信息
    @POST("mspt/Applet/applet_news")
    Observable<HttpResult<List<Recommend>>> getRecommendHttp();

    //获取验证码
//    @POST("")
}
