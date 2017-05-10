package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.SearchContract;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.presenter.SearchPresenter;
import milai.meishipintu.com.faxianlite.view.adapter.HistoryAdapter;
import milai.meishipintu.com.faxianlite.view.adapter.SearchAdapter;

public class SearchActivity extends AppCompatActivity implements SearchContract.IView,SearchActivityListener{

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.rv_result)
    RecyclerView rvResult;

    private SearchContract.IPresenter mPresenter;
    private List<String> history;
    private HistoryAdapter historyAdapter;
    private List<Recommend> searchResult;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mPresenter = new SearchPresenter(this);
        mPresenter.loadHistory();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            String search = etSearch.getText().toString();
            if (StringUtils.isNullOrEmpty(search)) {
                showError("搜索内容不能为空");
            } else {
                mPresenter.doSearch(search);
                mPresenter.addToHistory(history, search);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.back, R.id.et_search, R.id.tv_zhishi, R.id.tv_dianji, R.id.tv_chengshi, R.id.tv_yihui, R.id.iv_clear_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.et_search:
                if (rvResult.isShown()) {
                    rvResult.setVisibility(View.GONE);
                }
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
        if (historyAdapter == null) {
            history = historys;
            historyAdapter = new HistoryAdapter(this, history);
            rvHistory.setLayoutManager(new LinearLayoutManager(this));
            rvHistory.setItemAnimator(new DefaultItemAnimator());
            rvHistory.setAdapter(historyAdapter);
        } else {
            history.clear();
            history.addAll(historys);
            historyAdapter.notifyDataSetChanged();
        }
    }

    //from SearchContract.IView
    @Override
    public void onSearchInfoGet(List<Recommend> result) {
        //刷新搜索记录
        mPresenter.loadHistory();
        //结果页显示
        rvResult.setVisibility(View.VISIBLE);
        if (searchAdapter == null) {
            searchResult = result;
            searchAdapter = new SearchAdapter(searchResult, this);
            rvResult.setLayoutManager(new LinearLayoutManager(this));
            rvResult.setItemAnimator(new DefaultItemAnimator());
            rvResult.setAdapter(searchAdapter);
        } else {
            searchResult.clear();
            searchResult.addAll(result);
            searchAdapter.notifyDataSetChanged();
        }
    }

    //from SearchActivityListener
    @Override
    public void onHistoryClick(String content) {
        mPresenter.doSearch(content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }


}
