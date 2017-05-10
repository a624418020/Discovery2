package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.constract.OrderContract;
import milai.meishipintu.com.faxianlite.model.beans.Coupon;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import milai.meishipintu.com.faxianlite.presenter.OrderPresenter;

public class OrderActivity extends AppCompatActivity implements OrderContract.IView {

    @BindView(R.id.tv_People_name)
    TextView tvPeopleName;
    @BindView(R.id.tv_People_phone)
    TextView tvPeoplePhone;
    @BindView(R.id.tv_name)
    TextView tvname;
    @BindView(R.id.tv_prompt)
    TextView tvprompt;
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

    private RequestManager manager;
    private Recommend recommend;
    private OrderContract.IPresenter orderPresenter;
    private Red red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        recommend = (Recommend) getIntent().getSerializableExtra("Recommend");
        manager = Glide.with(this);
        orderPresenter = new OrderPresenter(this);
        orderPresenter.getCouponInfo(161 + "");
    }

    private void settext(Red red) {
        if (red != null) {
            String url = "http://" + red.getCoupon().getShare_img();
            tvPeopleName.setText("未发现");
            tvPeoplePhone.setText(red.getPhone());
            tvPeopleAddress.setText(red.getAddress());
            manager.load(url).into(ivCommodityImage);//加载网络图片
            tvCommodityTitle.setText(red.getCoupon().getTitle());
            tvCommoditySubtitle.setText(red.getCoupon().getDescription());
            tvCommodityValue.setText("未发现");//价值
            tvQuantity.setText("1");
            tvStarTime.setText(red.getStart_time() + "至" + red.getEnd_time());
        }

    }

    @OnClick({R.id.bt_return, R.id.bt_join})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                finish();
                break;
            case R.id.bt_join:
                orderPresenter.paticipate(null, "jcdkvy36", "18115168206");
                break;
        }
    }

    //from OrderContract.IView
    @Override
    public void showError(String err) {

    }


    @Override
    public void showCouponInfo(List<Red> list) {
        red = list.get(0);
        settext(red);
        Log.i("News_id", list.get(0).getNews_id());
    }

    //from OrderContract.IView
    @Override
    public void onPaticipateSucess(Coupon coupon, String onerror) {
        if (coupon != null && onerror == null) {
//            tvprompt.setText(coupon.getCouponShow());
            tvprompt.setText("记得安排好时间哦");
        } else {
            tvname.setText("参与失败");
            tvprompt.setText(onerror);
        }
        rlSuccess.setVisibility(View.VISIBLE);
        Log.i("News_id", coupon.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderPresenter.unsubscrib();
    }

}
