package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ObservableWebView;
import milai.meishipintu.com.faxianlite.constract.DetailContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.presenter.DetailsPresenter;

public class DetailsActivity extends AppCompatActivity implements DetailContract.IView {


    @BindView(R.id.tv_title0)
    TextView title;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_yc)
    TextView tvYc;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private List<Order> list;
    private DetailContract.IPresenter mPresenter;
    private Recommend recommend;
    private ObservableWebView mwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        //获取商品信息
        mPresenter = new DetailsPresenter(this);
        mPresenter.getActivityInfo(0);
        //发现页面跳转带过来的ID
        recommend = (Recommend) getIntent().getSerializableExtra("Recommend");
        Log.i("recommend", recommend.toString());
        Toast.makeText(getApplicationContext(), recommend.getTitle() + "跳转过来的参数", Toast.LENGTH_SHORT).show();
        web(savedInstanceState, recommend);
    }

    private void web(Bundle savedInstanceState, Recommend recommend) {
        tvTitle.setText("商品详情");
        title.setText(recommend.getTitle());
        tvTime.setText(recommend.getDate());
        tvName.setText(recommend.getSub_name());
        if (recommend.getIs_yc() != null && recommend.getIs_yc().length() > 2) {
            tvYc.setText(recommend.getIs_yc());
            tvYc.setVisibility(View.VISIBLE);
        }
        mwebview = (ObservableWebView) findViewById(R.id.wv_article);
        mwebview.getSettings().setJavaScriptEnabled(true);//设置WebView属性，能够执行Javascript脚本
        if (null != savedInstanceState) {
            mwebview.restoreState(savedInstanceState);
        } else {
            mwebview.loadUrl("http://faxian.milaipay.com/Home/Index/news?id=" + recommend.getId());
        }
        WebSettings settings = mwebview.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mwebview.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback() {
            public void onScroll(int dx, int dy) {
                Log.i("dy",dy+"");
                //这里我们根据dx和dy参数做自己想做的事情
            }
        });
    }


    @OnClick({R.id.bt_want, R.id.bt_join,R.id.bt_return})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.bt_want:

                //Intent传递参数
                intent.putExtra("list", (Serializable) list);
                intent.setClass(this, WantActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_join:
                //Intent传递参数
                intent.putExtra("list", (Serializable) list);
                intent.setClass(this, OrderActivity.class);
                startActivity(intent);
                break;
            case  R.id.bt_return:{
                finish();

            }
        }
    }


    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mwebview.saveState(outState);
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

