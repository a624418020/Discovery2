package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.constract.WantContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.presenter.WantPresenter;
import milai.meishipintu.com.faxianlite.view.adapter.WantAdapter;

public class WantActivity extends AppCompatActivity implements WantContract.IView, WantAdapter.IonSlidingViewClickListener, SwipeRefreshLayout.OnRefreshListener {


    private String TAG = "RecyclerViewDemo";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private WantContract.IPresenter mPresenter;
    private WantAdapter adapter;
    private static final int REFRESH_STATUS = 0;
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want);
        ButterKnife.bind(this);
        mPresenter = new WantPresenter(this);
        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        //下面的两种方式自己可以试试看下效果就知道了
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        adapter = new WantAdapter(this);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        mPresenter.getWantList();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(WantActivity.this, "单击" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        Toast.makeText(WantActivity.this, "删除" + position, Toast.LENGTH_SHORT).show();
        adapter.removeData(position);
    }

    @Override
    //下拉刷新的监听
    public void onRefresh() {

    }

    //from WantContract.IView
    @Override
    public void showWantList(List<Order> orderList) {

    }

    //from WantContract.IView
    @Override
    public void reFreshList() {

    }

    //from WantContract.IView
    @Override
    public void showError(String err) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }
}