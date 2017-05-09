package milai.meishipintu.com.faxianlite.presenter;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.constract.MineContract;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class MinePresenter implements MineContract.IPresenter {

    private MineContract.IView mineViewInterface;

    public MinePresenter(MineContract.IView mineViewInterface ) {
        this.mineViewInterface = mineViewInterface;
    }

    public int[] getIcon(){
         int[] icon = {R.drawable.icon_redbag, R.drawable.icon_caipiao, R.drawable.icon_phone_recharge,
                R.drawable.icon_subway, R.drawable.icon_mobi, R.drawable.icon_didi,
                R.drawable.icon_weather, R.drawable.icon_eat, R.drawable.icon_nanjing
        };
        return icon;
    }
    public String[] getIconName(){
        String[] iconName = {"抢红包", "彩票", "手机充值", "地铁时刻表", "摩拜单车", "滴滴出行", "天气", "吃喝玩乐",
                "创意南京",};
        return iconName;
    }

    //from MineContract.IPresenter
    @Override
    public void getGridData() {
        int[] icon = {R.drawable.icon_redbag, R.drawable.icon_caipiao, R.drawable.icon_phone_recharge,
                R.drawable.icon_subway, R.drawable.icon_mobi, R.drawable.icon_didi,
                R.drawable.icon_weather, R.drawable.icon_eat, R.drawable.icon_nanjing
        };
        String[] iconName = {"抢红包", "彩票", "手机充值", "地铁时刻表", "摩拜单车", "滴滴出行", "天气", "吃喝玩乐",
                "创意南京",};
        mineViewInterface.showGrid(icon, iconName);
    }

    //from MineContract.IPresenter
    @Override
    public void unsubscrib() {
    }

    //from MineContract.IPresenter
    @Override
    public void getNotice() {

    }

    //from MineContract.IPresenter
    @Override
    public void getUserInfo() {
        mineViewInterface.onUserInfoGet(DiscoverApplication.getUser());
    }
}
