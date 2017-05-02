package milai.meishipintu.com.faxianlite.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
      return   list.get(position);
    }

    @Override
    public int getCount() {
        return  list.size();
    }
}
