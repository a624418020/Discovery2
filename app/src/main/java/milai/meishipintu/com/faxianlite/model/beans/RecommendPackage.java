package milai.meishipintu.com.faxianlite.model.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * <p>
 * 主要功能：
 */

public class RecommendPackage implements Serializable {
    private static final long serialVersionUID = 4l;

    private Recommend headRecommend;
    private List<Recommend> smallRecommends;

    public Recommend getHeadRecommend() {
        return headRecommend;
    }

    public void setHeadRecommend(Recommend headRecommend) {
        this.headRecommend = headRecommend;
    }

    public List<Recommend> getSmallRecommends() {
        return smallRecommends;
    }

    public void setSmallRecommends(List<Recommend> smallRecommends) {
        this.smallRecommends = smallRecommends;
    }
}
