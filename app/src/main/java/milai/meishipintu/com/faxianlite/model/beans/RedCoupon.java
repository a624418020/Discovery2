package milai.meishipintu.com.faxianlite.model.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class RedCoupon implements Serializable {
    private String id;
    private String bundle_key;
    private String uniqids;
    private String shops_id;
    private String title;
    private String intro;
    private String url;
    private String tam_id;
    private String tam_info;
    private String uselimit;
    private String wename;
    private String act_stime;
    private String act_etime;
    private String start_time;
    private String end_time;
    private String get_cycle;
    private String get_times;
    private String is_html;
    private String description;
    private String share_img;
    private String share_title;
    private String timeList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBundle_key() {
        return bundle_key;
    }

    public void setBundle_key(String bundle_key) {
        this.bundle_key = bundle_key;
    }

    public String getUniqids() {
        return uniqids;
    }

    public void setUniqids(String uniqids) {
        this.uniqids = uniqids;
    }

    public String getShops_id() {
        return shops_id;
    }

    public void setShops_id(String shops_id) {
        this.shops_id = shops_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTam_id() {
        return tam_id;
    }

    public void setTam_id(String tam_id) {
        this.tam_id = tam_id;
    }

    public String getTam_info() {
        return tam_info;
    }

    public void setTam_info(String tam_info) {
        this.tam_info = tam_info;
    }

    public String getUselimit() {
        return uselimit;
    }

    public void setUselimit(String uselimit) {
        this.uselimit = uselimit;
    }

    public String getWename() {
        return wename;
    }

    public void setWename(String wename) {
        this.wename = wename;
    }

    public String getAct_stime() {
        return act_stime;
    }

    public void setAct_stime(String act_stime) {
        this.act_stime = act_stime;
    }

    public String getAct_etime() {
        return act_etime;
    }

    public void setAct_etime(String act_etime) {
        this.act_etime = act_etime;
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

    public String getGet_cycle() {
        return get_cycle;
    }

    public void setGet_cycle(String get_cycle) {
        this.get_cycle = get_cycle;
    }

    public String getGet_times() {
        return get_times;
    }

    public void setGet_times(String get_times) {
        this.get_times = get_times;
    }

    public String getIs_html() {
        return is_html;
    }

    public void setIs_html(String is_html) {
        this.is_html = is_html;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare_img() {
        return share_img;
    }

    public void setShare_img(String share_img) {
        this.share_img = share_img;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getTimeList() {
        return timeList;
    }

    public void setTimeList(String timeList) {
        this.timeList = timeList;
    }

    @Override
    public String toString() {
        return "RedCoupon{" +
                "id='" + id + '\'' +
                ", bundle_key='" + bundle_key + '\'' +
                ", uniqids='" + uniqids + '\'' +
                ", shops_id='" + shops_id + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", url='" + url + '\'' +
                ", tam_id='" + tam_id + '\'' +
                ", tam_info='" + tam_info + '\'' +
                ", uselimit='" + uselimit + '\'' +
                ", wename='" + wename + '\'' +
                ", act_stime='" + act_stime + '\'' +
                ", act_etime='" + act_etime + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", get_cycle='" + get_cycle + '\'' +
                ", get_times='" + get_times + '\'' +
                ", is_html='" + is_html + '\'' +
                ", description='" + description + '\'' +
                ", share_img='" + share_img + '\'' +
                ", share_title='" + share_title + '\'' +
                ", timeList='" + timeList + '\'' +
                '}';
    }
}
