package milai.meishipintu.com.faxianlite.model.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 主要功能：
 */

public class WantItem implements Serializable {
    private static final long serialVersionUID = 3L;          //序列化验证码

    private String title;
    private String logo;
    private int id;
    private String activity_id;
    private long time;
    private String sub_name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    @Override
    public String toString() {
        return "WantItem{" +
                "title='" + title + '\'' +
                ", logo='" + logo + '\'' +
                ", id=" + id +
                ", activity_id='" + activity_id + '\'' +
                ", time=" + time +
                ", sub_name='" + sub_name + '\'' +
                '}';
    }
}
