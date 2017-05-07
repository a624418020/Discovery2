package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.Immersive;
import milai.meishipintu.com.faxianlite.view.adapter.MyFragmentAdapter;
import milai.meishipintu.com.faxianlite.view.fargment.MineFragment;
import milai.meishipintu.com.faxianlite.view.fargment.DiscoverFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Discover-MainActivity";

    private  ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Immersive.immersive(0xff212121, this);
        setContentView(R.layout.activity_main);
        if (DiscoverApplication.getUser() == null) {
            //未登录
            startActivity(new Intent(MainActivity.this, LoginOrLoginActivity.class));
        }
        //生成fragment
        ViewPager pager = (ViewPager)findViewById(R.id.viewpage);
        fragments = new ArrayList<>();
        fragments.add(new DiscoverFragment());
        fragments.add(new MineFragment());
        pager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragments));
        //将fragment传给RecommendPresenter
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "intent:" + intent.getIntExtra("type", -1));
        if (intent.getIntExtra("type", -1) == Constant.LOGOUT_SUCCESS) {
            startActivity(new Intent(MainActivity.this, LoginOrLoginActivity.class));
        }
    }
}

