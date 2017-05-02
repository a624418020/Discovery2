package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.view.adapter.MyFragmentAdapter;
import milai.meishipintu.com.faxianlite.view.fargment.MineFragment;
import milai.meishipintu.com.faxianlite.view.fargment.DiscoverFragment;

public class MainActivity extends AppCompatActivity {
private  ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//生成fragment
        ViewPager pager = (ViewPager)findViewById(R.id.viewpage);
        fragments = new ArrayList<Fragment>();
        fragments.add(new DiscoverFragment());
        fragments.add(new MineFragment());
        pager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragments));
//将fragment传给RecommendPresenter

    }


}

