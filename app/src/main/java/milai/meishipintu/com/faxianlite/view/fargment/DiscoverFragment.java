package milai.meishipintu.com.faxianlite.view.fargment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.BetterRecyclerView;
import milai.meishipintu.com.faxianlite.Tool.PagingScrollHelper;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.DiscoverContract;
import milai.meishipintu.com.faxianlite.model.beans.RecommendPackage;
import milai.meishipintu.com.faxianlite.presenter.DiscoverPresenter;
import milai.meishipintu.com.faxianlite.view.activity.SearchActivity;
import milai.meishipintu.com.faxianlite.view.activity.SelectCityActivity;
import milai.meishipintu.com.faxianlite.view.adapter.DiscoverAdapter;


public class DiscoverFragment extends Fragment implements DiscoverContract.IView, PagingScrollHelper.onPageChangeListener {


    @BindView(R.id.recyclerView)
    BetterRecyclerView recyclerView;
    @BindView(R.id.tv_city)
    TextView tvCity;

    private DiscoverContract.IPresenter mPresenter;
    private DiscoverAdapter discoverAdapter;
    private List<RecommendPackage> data;
    private int nummber=5;//期号

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient;
    public AMapLocationListener mLocationListener;


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

    @Override
    public void onResume() {
        super.onResume();
        //每次恢复显示时定位
        startLocatiion();
    }

    //定位
    private void startLocatiion() {
        int hasLoactionPermission = ContextCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION);
        //已授权，开始定位
        if (hasLoactionPermission == PackageManager.PERMISSION_GRANTED) {
            if (mLocationClient == null) {
                //初始化定位
                mLocationClient = new AMapLocationClient(DiscoverApplication.getInstance());
                mLocationListener = new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                            tvCity.setText(aMapLocation.getCity());//城市信息
                            ToastUtils.show(getActivity(), aMapLocation.getCity(), true);
                        }
                    }
                };
                AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
                //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
                mLocationOption.setOnceLocation(true);          //仅单次定位
                mLocationClient.setLocationListener(mLocationListener);
                mLocationClient.setLocationOption(mLocationOption);
            }
            //启动定位
            mLocationClient.startLocation();
        }
    }

    private void initData() {
        data = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        discoverAdapter = new DiscoverAdapter(getActivity(), data);
        recyclerView.setAdapter(discoverAdapter);
        Log.i("position",discoverAdapter.getposition()+"");
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //获取最新5期信息
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == RecyclerView.SCROLL_STATE_IDLE&&layoutManager.findLastCompletelyVisibleItemPosition()>=layoutManager.getItemCount()-1){

                    String number= data.get(layoutManager.findLastCompletelyVisibleItemPosition()).getHeadRecommend().getNumber();
                    Log.i("Position",number);
                    mPresenter.getrecommendData(number);
                }

            }
        });
        mPresenter.getrecommendData("0");
    }

    @Override
    public void onPageChange(int index) {

    }

    @Override
    public void showError(String err) {
        ToastUtils.show(getActivity(), err, true);
    }

    @Override
    public void showRecommendData(List<RecommendPackage> data) {
        this.data.addAll(data);
        discoverAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscrib();

    }

    @OnClick({R.id.bt_city, R.id.bt_search})
    public void onClick(View view) {
        Intent intent = new Intent();
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
