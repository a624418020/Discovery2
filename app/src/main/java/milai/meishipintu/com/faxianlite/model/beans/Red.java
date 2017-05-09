package milai.meishipintu.com.faxianlite.model.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class Red implements Serializable {

    private String id;
    private String flag_detail;
    private String applet_id;
    private String flag;
    private String address;
    private String phone;
    private String news_id;
    private String start_time;
    private String end_time;
    private String time;
    private RedCoupon coupon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag_detail() {
        return flag_detail;
    }

    public void setFlag_detail(String flag_detail) {
        this.flag_detail = flag_detail;
    }

    public String getApplet_id() {
        return applet_id;
    }

    public void setApplet_id(String applet_id) {
        this.applet_id = applet_id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public RedCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(RedCoupon coupon) {
        this.coupon = coupon;
    }
}
