package milai.meishipintu.com.faxianlite.view.fargment;

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
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.ParticipateContract;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.presenter.ParticipatePresenter;
import milai.meishipintu.com.faxianlite.view.adapter.ParticipateAdapter;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ParticipateFragment extends Fragment implements ParticipateContract.IView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int type;                       //1-可用 2-不可用
    private ParticipateContract.IPresenter mPresenter;
    private ParticipateAdapter adapter;
    private List<Order> list;

    public ParticipateFragment() {

    }

    //工厂函数中setArgument
    public static ParticipateFragment newInstance(int type) {
        ParticipateFragment fragment = new ParticipateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    //onCreate中getArgument
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.type = getArguments().getInt("type");
        }
        mPresenter = new ParticipatePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_participate_invalid, container, false);
        ButterKnife.bind(this, view);
        initdata();
        return view;
    }
    private void initdata(){
        list=new ArrayList<>();
        adapter =new ParticipateAdapter(getActivity(),list);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        mPresenter.getData(type);
    }


    @Override
    public void showError(String err) {
        ToastUtils.show(getActivity(), err, true);
    }

    @Override
    public void showData(List<Order> orderList) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
