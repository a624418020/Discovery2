package milai.meishipintu.com.faxianlite.presenter;

import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.constract.DetailContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class DetailsPresenter implements DetailContract.IPresenter {

    private DetailContract.IView detailsViewInterface;
    private List<Order>list;

    public DetailsPresenter(DetailContract.IView detailsViewInterface){
        this.detailsViewInterface=detailsViewInterface;
    }
    public void getdata(){
        list=new ArrayList<>();
        Order order=new Order();
        order.setPeople_name("李忠");
        order.setPeople_phone("18115168206");
        order.setPeople_address("南京 浦口 浦东");
        order.setCommodity_image("http://b.milaipay.com/Public/Uploads/applet/58fda4915ed75.jpg");
        order.setCommodity_title("日本设计师井上保美的");
        order.setCommodity_subtitle("卖衣服");
        order.setCommodity_value("199*1");
        order.setCommodity_amount("1");
        order.setStar_time("03月02");
        order.setEnd_time("04月26");
        order.setUser_name("阿呆");
        order.setUser_phone("13888888888");
        list.add(order);
        detailsViewInterface.showActivity();
    }

    //from DetailContract.IPresenter
    @Override
    public void getActivityInfo(int index) {

    }

    //from DetailContract.IPresenter
    @Override
    public void addWant(int index) {

    }

    //from DetailContract.IPresenter
    @Override
    public void unsubscrib() {

    }
}
