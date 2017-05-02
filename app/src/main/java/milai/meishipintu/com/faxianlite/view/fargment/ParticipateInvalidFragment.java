package milai.meishipintu.com.faxianlite.view.fargment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Order;
import milai.meishipintu.com.faxianlite.view.adapter.ParticipateInvalidAdapter;
import milai.meishipintu.com.faxianlite.presenter.ParticipateInvalidPresenter;
import milai.meishipintu.com.faxianlite.view.viewinterface.ParticipateInvalidViewInterface;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ParticipateInvalidFragment extends Fragment implements ParticipateInvalidViewInterface {

    private List<Order> list;
    private ParticipateInvalidAdapter participateInvalidAdapter;
    private ParticipateInvalidPresenter pp;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_participate_invalid, container, false);
        initdata(view);
        return view;
    }
    private void initdata(View view){
        list=new ArrayList<>();
        pp=new ParticipateInvalidPresenter(this);
        pp.getdata();
        participateInvalidAdapter=new ParticipateInvalidAdapter(getActivity(),list);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(participateInvalidAdapter);
    }

    @Override
    public void getdata(List<Order> list) {
        this.list.addAll(list);
    }
}
