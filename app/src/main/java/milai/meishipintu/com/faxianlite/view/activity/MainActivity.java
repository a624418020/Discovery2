package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.Immersive;
import milai.meishipintu.com.faxianlite.view.adapter.MyFragmentAdapter;
import milai.meishipintu.com.faxianlite.view.fargment.DiscoverFragment;
import milai.meishipintu.com.faxianlite.view.fargment.MineFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Discover-MainActivity";
    @BindView(R.id.viewpage)
    ViewPager pager;

    private ArrayList<Fragment> fragments;


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
        fragments = new ArrayList<>();
        fragments.add(new DiscoverFragment());
        fragments.add(new MineFragment());
        pager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragments));
        //将fragment传给RecommendPresenter
    }

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
                break;
            case R.id.mine:
                break;
        }
    }
}

