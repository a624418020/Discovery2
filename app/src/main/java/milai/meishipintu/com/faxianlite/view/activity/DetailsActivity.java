package milai.meishipintu.com.faxianlite.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.MScrollView;
import milai.meishipintu.com.faxianlite.Tool.ObservableWebView;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.DetailContract;
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
    @BindView(R.id.scrollview)
    MScrollView scrollview;
    @BindView(R.id.button)
    LinearLayout llBottom;

    private DetailContract.IPresenter mPresenter;
    private Recommend recommend;
    private ObservableWebView mwebview;
    private boolean show = true;            //默认底部显现

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        tvTitle.setText(R.string.app);
        //获取商品信息
        mPresenter = new DetailsPresenter(this);
        //发现页面跳转带过来的ID
        recommend = (Recommend) getIntent().getSerializableExtra("Recommend");
        Log.i("recommend", recommend.toString());
        initWeb(savedInstanceState, recommend);
        if (StringUtils.isNullOrEmptOrZero(recommend.getActivity_id())) {
            llBottom.setVisibility(View.GONE);
        }
    }

    private void initWeb(Bundle savedInstanceState, Recommend recommend) {
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
        scrollview.setOnScrollChangedCallback(new MScrollView.OnScrollChangedCallback() {
            @Override
            public void onScroll(boolean shouldShow) {
                if (show != shouldShow) { //防止滚动时一直触发
                    //启动动画
                    startAnimationUpOrDown(llBottom, shouldShow);
                    //更新状态值
                    show = shouldShow;
                }
            }

            @Override
            public void onReachBottom() {
                if (!show) {
                    //启动动画
                    startAnimationUpOrDown(llBottom, true);
                    //更新状态值
                    show = true;
                }
            }
        });
    }

    //播放上下滑动画
    private void startAnimationUpOrDown(LinearLayout llBottom, boolean isUp) {
        Log.d("DetailActivity", "onAnimatiion start");
        int height = llBottom.getHeight();
        ObjectAnimator translationY;
        if (isUp) {
            translationY = ObjectAnimator.ofFloat(llBottom, "translationY", height, 0f);
        } else {
            translationY = ObjectAnimator.ofFloat(llBottom, "translationY", 0f, height);
        }
        translationY.setDuration(300);
        translationY.start();
    }

    @OnClick({R.id.bt_want, R.id.bt_join, R.id.bt_return})
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()) {
            case R.id.bt_want:
                Log.d("DetailActivity", "uid:" + DiscoverApplication.getUser().getUid() + ",id:" + recommend.getId());
                mPresenter.addWant(DiscoverApplication.getUser().getUid(), recommend.getId());
                break;
            case R.id.bt_join:
                //Intent传递参数
                intent = new Intent(this, OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Recommend",recommend);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.bt_return: {
                finish();
            }
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
        ToastUtils.show(this, err, true);
    }

    //from DetailContract.IView
    @Override
    public void onWantSuccess() {
        ToastUtils.show(this, "收藏成功", true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }
}

