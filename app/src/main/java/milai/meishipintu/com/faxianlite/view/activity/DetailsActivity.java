package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.constract.DetailContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.presenter.DetailsPresenter;
import milai.meishipintu.com.faxianlite.view.adapter.TestNormalAdapter;

public class DetailsActivity extends AppCompatActivity implements DetailContract.IView{

    @BindView(R.id.roll_view_pager)
    RollPagerView mRollViewPager;

    private List<Order> list;
    private DetailContract.IPresenter mPresenter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        list=new ArrayList<>();
        //获取商品信息
        mPresenter=new DetailsPresenter(this);
        mPresenter.getActivityInfo(0);
        //发现页面跳转带过来的ID
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mRollViewPager.setPlayDelay(2000);
        mRollViewPager.setAnimationDurtion(500);
        mRollViewPager.setAdapter(new TestNormalAdapter());
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

    }

    @OnClick({R.id.bt_want, R.id.bt_join})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.bt_want:

                //Intent传递参数
                intent.putExtra("list", (Serializable)list);
                intent.setClass(this, WantActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_join:
                //Intent传递参数
                intent.putExtra("list", (Serializable)list);
                intent.setClass(this, OrderActivity.class);
                startActivity(intent);
                break;
        }
    }

    //from DetailContract.IView
    @Override
    public void showError(String err) {

    }

    //from DetailContract.IView
    @Override
    public void showActivity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }
}

