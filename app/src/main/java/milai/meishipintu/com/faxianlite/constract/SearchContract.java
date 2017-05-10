package milai.meishipintu.com.faxianlite.constract;

import java.util.List;

import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.presenter.BasicPresenter;
import milai.meishipintu.com.faxianlite.view.BasicView;

/**
 * Created by Administrator on 2017/5/10.
 * <p>
 * 主要功能：
 */

public interface SearchContract {

    interface IPresenter extends BasicPresenter {

        void loadHistory();

        void clearHistory();

        void doSearch(String content);

        void addToHistory(List<String> history, String content);

    }

    interface IView extends BasicView {

        void onHistoryGet(List<String> historys);

        void onSearchInfoGet(List<Recommend> result);
    }

}
