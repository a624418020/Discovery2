package milai.meishipintu.com.faxianlite.presenter;

import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.constract.WantContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class WantPresenter implements WantContract.IPresenter {

    private WantContract.IView wantViewInterface;

    public WantPresenter (WantContract.IView wantViewInterface){
        this.wantViewInterface=wantViewInterface;
    }

    @Override
    public void getWantList() {
        List<Order> list=new ArrayList<>();
        for(int i=0;i<5;i++){
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
        }
        Log.i("aaaaa",list.size()+"");

        wantViewInterface.showWantList(list);
    }

    @Override
    public void deletWant(int[] deletNum) {

    }

    @Override
    public void unsubscrib() {

    }
}
