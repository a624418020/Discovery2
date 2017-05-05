package milai.meishipintu.com.faxianlite.model.beans;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class Recommend implements Serializable,Comparable<Recommend> {
    private static final long serialVersionUID = 2L;          //序列化验证码

    private String id;
    private String title;
    private String logo;
    private String type;                //显示类型
    private String read_count;          //阅读数
    private String sub_name;
    private int orders;              //小图列表内排序
    private String number;              //期号
    private int is_main;             //是否头图
    private String is_show;             //是否显示
    private String is_yc;               //是否原创
    private String author;
    private String date;                //显示日期
    private String dz;                  //点赞
    private String activity_id;         //对应活动id
    private String tips;
    private String time;                //生成时间戳

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIs_main() {
        return is_main;
    }

    public void setIs_main(int is_main) {
        this.is_main = is_main;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getIs_yc() {
        return is_yc;
    }

    public void setIs_yc(String is_yc) {
        this.is_yc = is_yc;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "title='" + title + '\'' +
                ", logo='" + logo + '\'' +
                ", read_count='" + read_count + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", orders='" + orders + '\'' +
                ", number='" + number + '\'' +
                ", is_main='" + is_main + '\'' +
                ", is_yc='" + is_yc + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", dz='" + dz + '\'' +
                ", activity_id='" + activity_id + '\'' +
                '}';
    }


    //对象实现Comparable接口后即可通过Collection.sort()进行比较排序，
    //实现该方法的返回值0代表相等，1表示大于，-1表示小于
    @Override
    public int compareTo(@NonNull Recommend o) {
        return this.orders - o.getOrders();
    }
}
