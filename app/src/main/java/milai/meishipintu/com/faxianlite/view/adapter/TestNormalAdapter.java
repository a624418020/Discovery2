package milai.meishipintu.com.faxianlite.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import milai.meishipintu.com.faxianlite.R;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：
 */

public class TestNormalAdapter extends StaticPagerAdapter{
    private int[] imgs = {
            R.drawable.icon_avantar,
            R.drawable.icon_avantar,
            R.drawable.icon_avantar,
            R.drawable.icon_avantar,
    };


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    @Override
    public int getCount() {
        return imgs.length;
    }
}
