package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.Immersive;
import milai.meishipintu.com.faxianlite.view.fargment.DiscoverFragment;
import milai.meishipintu.com.faxianlite.view.fargment.MineFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Discover-MainActivity";
    @BindView(R.id.fram)
    FrameLayout frame;
    @BindView(R.id.iv_recommend)
    ImageView ivRecommend;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;

    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Immersive.immersive(0xff212121, this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (DiscoverApplication.getUser() == null) {
            //未登录
            startActivity(new Intent(MainActivity.this, LoginOrLoginActivity.class));
        }
        //生成fragment
        discoverFragment = new DiscoverFragment();
        mineFragment = new MineFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fram, discoverFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
        ivMine.setEnabled(false);
    }

    //
    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "intent:" + intent.getIntExtra("type", -1));
        int type = intent.getIntExtra("type", -1);
        if (type == Constant.LOGOUT_SUCCESS) {
            //重启登录界面
            startActivity(new Intent(MainActivity.this, LoginOrLoginActivity.class));
        } else if (type == Constant.GETOUT) {
            //退出
            this.finish();
        }
    }

    @OnClick({R.id.recommend, R.id.mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recommend:
                if (!discoverFragment.isVisible()) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.show(discoverFragment);
                    transaction.hide(mineFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                    transaction.commit();
                    ivMine.setEnabled(false);
                    tvMine.setEnabled(false);
                    ivRecommend.setEnabled(true);
                    tvRecommend.setEnabled(true);
                }
                break;
            case R.id.mine:
                if (!mineFragment.isVisible()) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    if (!mineFragment.isAdded()) {
                        transaction.add(R.id.fram, mineFragment);
                    } else {
                        transaction.show(mineFragment);
                    }
                    transaction.hide(discoverFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                    transaction.commit();
                    ivMine.setEnabled(true);
                    tvMine.setEnabled(true);
                    ivRecommend.setEnabled(false);
                    tvRecommend.setEnabled(false);
                }
                break;
        }
    }
}

