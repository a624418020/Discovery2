package milai.meishipintu.com.faxianlite.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.constract.ParticipateContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ParticipatePresenter implements ParticipateContract.IPresenter{

    private ParticipateContract.IView view;

    public ParticipatePresenter(ParticipateContract.IView iView){
        view = iView;
    }


    @Override
    public void unsubscrib() {

    }

    @Override
    public void getData(int type) {
        List<Order> list = new ArrayList<>();
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
        view.showData(list);
    }
}
