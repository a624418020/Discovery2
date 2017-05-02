package milai.meishipintu.com.faxianlite.model.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class Recommend implements Serializable {
    private String date;
    private String author;
    private String id;
    private String is_main;
    private String is_show;
    private String is_yc;
    private String logo;
    private String number;
    private String orders;
    private String read_count;
    private String sub_name;
    private String time;
    private String title;
    private String type;
    private String url;

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

    public String getIs_main() {
        return is_main;
    }

    public void setIs_main(String is_main) {
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

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", id='" + id + '\'' +
                ", is_main='" + is_main + '\'' +
                ", is_show='" + is_show + '\'' +
                ", is_yc='" + is_yc + '\'' +
                ", logo='" + logo + '\'' +
                ", number='" + number + '\'' +
                ", orders='" + orders + '\'' +
                ", read_count='" + read_count + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
