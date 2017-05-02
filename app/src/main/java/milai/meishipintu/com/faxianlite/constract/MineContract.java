package milai.meishipintu.com.faxianlite.constract;

import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/2.
 * <p>
 * 主要功能：
 */

public interface MineContract {

    interface IPresenter extends BasicPresenter {

        //获取通知信息
        void getNotice();

        //设置个人头像
        void getHeadportrait();

        //获取第三方支持数据
        void getGridData();
    }

    interface IView extends BasicView {

        //判断是否有新消息
        void dealWithNewestNotice(int number);

        //设置头像
        void setHeadportrait(String url);

        //显示第三方app
        void showGrid(int[] iconId,String[] iconName);

    }
}
