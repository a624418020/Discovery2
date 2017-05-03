package milai.meishipintu.com.faxianlite.view.fargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.constract.DiscoverContract;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.Tool.DividerItemDecoration;
import milai.meishipintu.com.faxianlite.Tool.PagingScrollHelper;
import milai.meishipintu.com.faxianlite.view.adapter.DiscoverAdapter;
import milai.meishipintu.com.faxianlite.view.adapter.MyPagerAdapter;
import milai.meishipintu.com.faxianlite.view.adapter.RecommendAdapter;
import milai.meishipintu.com.faxianlite.presenter.DiscoverPresenter;


public class DiscoverFragment extends Fragment implements DiscoverContract.IView, PagingScrollHelper.onPageChangeListener {

    private RecyclerView recyclerView;
    private ViewPager vPager;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;
    private RecommendAdapter adapter;
    private DiscoverAdapter discoverAdapter;
    private DiscoverContract.IPresenter mPresenter;
    private DividerItemDecoration vDividerItemDecoration = null;
    private LinearLayoutManager vLinearLayoutManager = null;
    private RecyclerView.ItemDecoration lastItemDecoration = null;
    private List<Recommend> data;

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
        initData();
        return view;
    }
    private void initData() {

        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        vDividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        vLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        data=new ArrayList<>();
        discoverAdapter = new DiscoverAdapter(getActivity(),data);
        recyclerView.setAdapter(discoverAdapter);

        mPresenter.getrecommendData();

        scrollHelper.setUpRecycleView(recyclerView);
        scrollHelper.setOnPageChangeListener(this);
        recyclerView.setLayoutManager(vLinearLayoutManager);
        recyclerView.removeItemDecoration(lastItemDecoration);
        recyclerView.addItemDecoration(vDividerItemDecoration);
        scrollHelper.updateLayoutManger();
        lastItemDecoration = vDividerItemDecoration;

    }

    @Override
    public void onPageChange(int index) {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void showRecommendData(List<Recommend> data) {
        this.data.clear();
        this.data.addAll(data);
        discoverAdapter.notifyDataSetChanged();

        //为了更新数据
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
