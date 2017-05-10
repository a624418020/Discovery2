package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ChooseHeadViewDialog;
import milai.meishipintu.com.faxianlite.constract.WantContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.model.beans.WantItem;
import milai.meishipintu.com.faxianlite.presenter.WantPresenter;
import milai.meishipintu.com.faxianlite.view.adapter.WantAdapter;

public class WantActivity extends AppCompatActivity implements WantContract.IView,SwipeRefreshLayout.OnRefreshListener,OnItemClickListener {


    private String TAG = "RecyclerViewDemo";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private WantContract.IPresenter mPresenter;
    private WantAdapter adapter;
    private List<WantItem> dataList;
    private int itemPosition = -1;                       //记录将要删除的item的位置，-1表示当前没有要清除的item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want);
        ButterKnife.bind(this);
        mPresenter = new WantPresenter(this);
        initView();
    }

    private void initView() {
        dataList = new ArrayList<>();
        adapter = new WantAdapter(this, dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        mPresenter.getWantList(DiscoverApplication.getUser().getUid());
    }

    @Override
    //下拉刷新的监听
    public void onRefresh() {
        mPresenter.getWantList(DiscoverApplication.getUser().getUid());
    }

    //from WantContract.IView
    @Override
    public void showWantList(List<WantItem> wantList) {
        dataList.clear();
        dataList.addAll(wantList);
        adapter.notifyDataSetChanged();
    }

    //from WantContract.IView
    @Override
    public void showError(String err) {

    }

    //from WantContract.IView
    @Override
    public void onRecommedInfoGet(Recommend recommend) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Recommend",  recommend);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //from WantContract.IView
    @Override
    public void onDeletSuccess() {
        //有待删除项目时执行
        if (itemPosition > -1) {
            dataList.remove(itemPosition);
            adapter.notifyItemRemoved(itemPosition);
            adapter.notifyItemRangeChanged(itemPosition, dataList.size() - itemPosition);
            //还原itemPosition 可继续删除
            itemPosition = -1;
        }
    }

    //from WantContract.IView
    @Override
    public void onDeletFaild() {
        //还原itemPosition 可继续删除
        itemPosition = -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }

    //from WantActivity.onItemClickListener
    @Override
    public void onItemClick(WantItem wantItem) {
        mPresenter.getRecomendInfo(wantItem.getId());
    }

    //from WantActivity.onItemClickListener
    @Override
    public void onItemRemove(int position) {
        //做deletItem的空判断，保证同一时刻只能删除一个item
        if (itemPosition == -1) {
            itemPosition = position;
            mPresenter.deletWant(DiscoverApplication.getUser().getUid(), dataList.get(position).getId());
        }
    }


}