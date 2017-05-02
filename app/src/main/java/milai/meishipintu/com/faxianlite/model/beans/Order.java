package milai.meishipintu.com.faxianlite.model.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class Order implements Serializable {
    private String people_name;
    private String people_phone;
    private String people_address;
    private String commodity_image;
    private String commodity_title;
    private String commodity_subtitle;
    private String commodity_value;
    private String commodity_amount;
    private String star_time;
    private String end_time;
    private String user_name;
    private String user_phone;


    public String getPeople_name() {
        return people_name;
    }

    public void setPeople_name(String people_name) {
        this.people_name = people_name;
    }

    public String getPeople_phone() {
        return people_phone;
    }

    public void setPeople_phone(String people_phone) {
        this.people_phone = people_phone;
    }

    public String getPeople_address() {
        return people_address;
    }

    public void setPeople_address(String people_address) {
        this.people_address = people_address;
    }

    public String getCommodity_image() {
        return commodity_image;
    }

    public void setCommodity_image(String commodity_image) {
        this.commodity_image = commodity_image;
    }

    public String getCommodity_title() {
        return commodity_title;
    }

    public void setCommodity_title(String commodity_title) {
        this.commodity_title = commodity_title;
    }

    public String getCommodity_subtitle() {
        return commodity_subtitle;
    }

    public void setCommodity_subtitle(String commodity_subtitle) {
        this.commodity_subtitle = commodity_subtitle;
    }

    public String getCommodity_value() {
        return commodity_value;
    }

    public void setCommodity_value(String commodity_value) {
        this.commodity_value = commodity_value;
    }

    public String getCommodity_amount() {
        return commodity_amount;
    }

    public void setCommodity_amount(String commodity_amount) {
        this.commodity_amount = commodity_amount;
    }

    public String getStar_time() {
        return star_time;
    }

    public void setStar_time(String star_time) {
        this.star_time = star_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }


}
