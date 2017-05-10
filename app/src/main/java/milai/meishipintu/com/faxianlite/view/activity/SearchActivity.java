package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.SearchContract;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.presenter.SearchPresenter;

public class SearchActivity extends AppCompatActivity implements SearchContract.IView{

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.rv_result)
    RecyclerView rvResult;

    private SearchContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mPresenter = new SearchPresenter(this);
        mPresenter.loadHistory();
    }

    @OnClick({R.id.back, R.id.et_search, R.id.tv_zhishi, R.id.tv_dianji, R.id.tv_chengshi, R.id.tv_yihui, R.id.iv_clear_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.et_search:
                break;
            case R.id.tv_zhishi:
                mPresenter.doSearch("知食");
                break;
            case R.id.tv_dianji:
                mPresenter.doSearch("店记");
                break;
            case R.id.tv_chengshi:
                mPresenter.doSearch("城事");
                break;
            case R.id.tv_yihui:
                mPresenter.doSearch("艺荟");
                break;
            case R.id.iv_clear_history:
                mPresenter.clearHistory();
                break;
        }
    }

    //from SearchContract.IView
    @Override
    public void showError(String err) {
        ToastUtils.show(this, err, true);
    }

    //from SearchContract.IView
    @Override
    public void onHistoryGet(List<String> historys) {

    }

    //from SearchContract.IView
    @Override
    public void onSearchInfoGet(List<Recommend> result) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }
}
