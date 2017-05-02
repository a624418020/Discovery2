package milai.meishipintu.com.faxianlite.view.fargment;

import android.os.Bundle;
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
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.Tool.DividerItemDecoration;
import milai.meishipintu.com.faxianlite.Tool.PagingScrollHelper;
import milai.meishipintu.com.faxianlite.view.adapter.MyAdapter;
import milai.meishipintu.com.faxianlite.view.adapter.MyPagerAdapter;
import milai.meishipintu.com.faxianlite.view.adapter.RecommendAdapter;
import milai.meishipintu.com.faxianlite.presenter.Presenter;
import milai.meishipintu.com.faxianlite.presenter.RecommendPresenter;
import milai.meishipintu.com.faxianlite.view.viewinterface.AppView;



public class DiscoverFragment extends Fragment implements AppView, PagingScrollHelper.onPageChangeListener {
    private RecyclerView recyclerView;
    private ViewPager vPager;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;
    private RecommendAdapter adapter;
    private MyAdapter myAdapter;
    private RecommendPresenter recommendPresenter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initData(view);
        return view;
    }
    private void initData(View view) {

        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        vDividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        vLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recommendPresenter = new RecommendPresenter(this);
        data=new ArrayList<Recommend>();
        myAdapter = new MyAdapter(getActivity(),data);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(myAdapter);
        recommendPresenter.getdata();
        scrollHelper.setUpRecycleView(recyclerView);
        scrollHelper.setOnPageChangeListener(this);
        recyclerView.setLayoutManager(vLinearLayoutManager);
        recyclerView.removeItemDecoration(lastItemDecoration);
        recyclerView.addItemDecoration(vDividerItemDecoration);
        scrollHelper.updateLayoutManger();
        lastItemDecoration = vDividerItemDecoration;


//        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        data=new ArrayList<Recommend>();
//        recommendPresenter = new RecommendPresenter(this);
//        recommendPresenter.getdata();
//        Log.i("data",data.size()+"");
//        adapter = new RecommendAdapter(getActivity(),data);
//        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new RecommendAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view,String id) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), DetailsActivity.class);
//                startActivity(intent);
//                Toast.makeText(getActivity(),"id"+id,Toast.LENGTH_LONG).show();
//            }
//
//
//        });
    }


    @Override
    public void getrecommenddata(List<Recommend> data) {
        this.data.clear();
        this.data.addAll(data);
        myAdapter.notifyDataSetChanged();

        //为了更新数据
    }



    @Override
    public void setPresenter(Presenter presenter) {

    }

    @Override
    public void onPageChange(int index) {

    }
}
