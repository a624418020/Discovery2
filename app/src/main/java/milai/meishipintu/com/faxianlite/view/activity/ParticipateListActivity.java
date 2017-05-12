package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.constract.ParticipateContract;
import milai.meishipintu.com.faxianlite.model.beans.Collection;
import milai.meishipintu.com.faxianlite.model.beans.Red;
import milai.meishipintu.com.faxianlite.presenter.ParticipatePresenter;
import milai.meishipintu.com.faxianlite.view.adapter.ParticipateAdapter;

public class ParticipateListActivity extends AppCompatActivity implements ParticipateContract.IView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_participate)
    RecyclerView rvParticipate;
    private ParticipateContract.IPresenter participatePresenter;
    private ParticipateAdapter  participateAdapter;
    private List<Collection> list;
    private List<Red> red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_list);
        ButterKnife.bind(this);
        initData();

    }
    private void initData(){
        tvTitle.setText("我参与的");
        list=new ArrayList<>();
        participatePresenter=new ParticipatePresenter(this);
        participatePresenter.getData(DiscoverApplication.getUser().getUid());
        Log.i("UID",DiscoverApplication.getUser().getUid());
        rvParticipate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        participateAdapter=new ParticipateAdapter(this,list);
        rvParticipate.setAdapter(participateAdapter);
        participateAdapter.setOnItemClickListener(new ParticipateAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                Intent intent = new Intent(ParticipateListActivity.this, ParticipationDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Collection", list.get(id));
                intent.putExtras(bundle);
                startActivity(intent);
//                participatePresenter.getCouponInfo(list.get(id).getActivity_id());

            }
        });
    }

    @OnClick(R.id.bt_return)
    public void onClick() {
        finish();
    }

    @Override
    public void showData(List<Collection> list) {
        this.list.addAll(list);
        participateAdapter.notifyDataSetChanged();

    }

    @Override
    public void showCouponInfo(List<Red> red) {
        this.red=red;
    }

    @Override
    public void showError(String err) {

    }
}
