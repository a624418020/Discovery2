package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.constract.OrderContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.presenter.OrderPresenter;

public class OrderActivity extends AppCompatActivity implements OrderContract.IView {

    @BindView(R.id.tv_People_name)
    TextView tvPeopleName;
    @BindView(R.id.tv_People_phone)
    TextView tvPeoplePhone;
    @BindView(R.id.tv_People_address)
    TextView tvPeopleAddress;
    @BindView(R.id.iv_Commodity_image)
    ImageView ivCommodityImage;
    @BindView(R.id.tv_Commodity_title)
    TextView tvCommodityTitle;
    @BindView(R.id.tv_Commodity_subtitle)
    TextView tvCommoditySubtitle;
    @BindView(R.id.tv_Commodity_value)
    TextView tvCommodityValue;
    @BindView(R.id.bt_addition)
    RelativeLayout btAddition;
    @BindView(R.id.tv_Star_time)
    TextView tvStarTime;
    @BindView(R.id.tv_quantity)
    TextView tvQuantity;
    @BindView(R.id.rl_success)
    RelativeLayout rlSuccess;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_phone)
    EditText edPhone;

    private List<Order> list;
    private List<Order> data;
    private RequestManager manager;
    private String time;
    private OrderContract.IPresenter orderPresenter;
    private Order order;
    private int quantity=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        //从商品页面传递过来的商品信息
        list=new ArrayList<>();
        list.addAll((List<Order>)getIntent().getSerializableExtra("list"));
        order=list.get(0);
        manager = Glide.with(this);
        orderPresenter = new OrderPresenter(this);
        settext(order);
    }

    private void settext(Order order){
        tvPeopleName.setText(order.getPeople_name());
        tvPeoplePhone.setText(order.getPeople_phone());
        tvPeopleAddress.setText(order.getPeople_address());
        manager.load(order.getCommodity_image()).into(ivCommodityImage);//加载网络图片
        tvCommodityTitle.setText(order.getCommodity_title());
        tvCommoditySubtitle.setText(order.getCommodity_subtitle());
        tvCommodityValue.setText(order.getCommodity_value());//价值
        tvQuantity.setText(quantity+"");
        tvStarTime.setText(time);
    }

    @OnClick({R.id.bt_addition, R.id.bt_join})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_addition:
                quantity=quantity+1;
                tvQuantity.setText(quantity+"");
                break;
            case R.id.bt_join:
                order.setUser_name(edName.getText().toString());
                order.setUser_phone(edPhone.getText().toString());
                data=new ArrayList<>();
                data.add(order);
                rlSuccess.setVisibility(View.VISIBLE);
                break;
        }
    }

    //from OrderContract.IView
    @Override
    public void showError(String err) {

    }

    //from OrderContract.IView
    @Override
    public void showCouponInfo() {

    }

    //from OrderContract.IView
    @Override
    public void onPaticipateSucess() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderPresenter.unsubscrib();
    }
}
