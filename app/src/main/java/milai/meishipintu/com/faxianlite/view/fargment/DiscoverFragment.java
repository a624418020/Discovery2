package milai.meishipintu.com.faxianlite.view.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.DividerItemDecoration;
import milai.meishipintu.com.faxianlite.Tool.PagingScrollHelper;
import milai.meishipintu.com.faxianlite.constract.DiscoverContract;
import milai.meishipintu.com.faxianlite.model.beans.RecommendPackage;
import milai.meishipintu.com.faxianlite.presenter.DiscoverPresenter;
import milai.meishipintu.com.faxianlite.view.activity.SearchActivity;
import milai.meishipintu.com.faxianlite.view.activity.SelectCityActivity;
import milai.meishipintu.com.faxianlite.view.adapter.DiscoverAdapter;


public class DiscoverFragment extends Fragment implements DiscoverContract.IView, PagingScrollHelper.onPageChangeListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Intent intent;
    private DiscoverAdapter discoverAdapter;
    private DiscoverContract.IPresenter mPresenter;
    private DividerItemDecoration vDividerItemDecoration = null;
    private LinearLayoutManager vLinearLayoutManager = null;
    private RecyclerView.ItemDecoration lastItemDecoration = null;
    private List<RecommendPackage> data;

    //返回fragment自己
    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DiscoverPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        vDividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        vLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        intent = new Intent();
        data = new ArrayList<>();
        recyclerView.setLayoutManager(vLinearLayoutManager);
        discoverAdapter = new DiscoverAdapter(getActivity(), data);
        recyclerView.setAdapter(discoverAdapter);
        //获取最新5期信息
        mPresenter.getrecommendData("0");

//        scrollHelper.setUpRecycleView(recyclerView);
//        scrollHelper.setOnPageChangeListener(this);
//        recyclerView.setLayoutManager(vLinearLayoutManager);
//        recyclerView.removeItemDecoration(lastItemDecoration);
//        recyclerView.addItemDecoration(vDividerItemDecoration);
//        scrollHelper.updateLayoutManger();
//        lastItemDecoration = vDividerItemDecoration;

    }

    @Override
    public void onPageChange(int index) {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void showRecommendData(List<RecommendPackage> data) {
        this.data.addAll(data);
        discoverAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.bt_city, R.id.bt_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_city:
                intent.setClass(getActivity(), SelectCityActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_search:
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
