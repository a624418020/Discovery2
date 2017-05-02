package milai.meishipintu.com.faxianlite.presenter;

import java.util.List;

import milai.meishipintu.com.faxianlite.constract.OrderContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class OrderPresenter implements OrderContract.IPresenter {
    private OrderContract.IView orderViewInterface;
    private List<Order> list;

    public OrderPresenter(OrderContract.IView orderViewInterface){
        this.orderViewInterface=orderViewInterface;
    }


    //from OrderContract.IPresenter
    @Override
    public void getCouponInfo(int id) {

    }

    //from OrderContract.IPresenter
    @Override
    public void paticipate(String name, String number, int id) {

    }

    //from OrderContract.IPresenter
    @Override
    public void unsubscrib() {

    }
}
