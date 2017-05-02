package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.view.adapter.MyFragmentAdapter;
import milai.meishipintu.com.faxianlite.view.fargment.ParticipateFragment;

public class ParticipateListActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_list);
        ViewPager pager = (ViewPager)findViewById(R.id.vp_fragment);
        fragments = new ArrayList<Fragment>();
        fragments.add(ParticipateFragment.newInstance(1));
        fragments.add(ParticipateFragment.newInstance(2));
        pager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragments));
    }
}
