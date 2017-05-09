package milai.meishipintu.com.faxianlite.view.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.CircleImageView;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.MineContract;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.presenter.MinePresenter;
import milai.meishipintu.com.faxianlite.view.activity.ParticipateListActivity;
import milai.meishipintu.com.faxianlite.view.activity.PersonalInformationActivity;
import milai.meishipintu.com.faxianlite.view.activity.SettingActivity;
import milai.meishipintu.com.faxianlite.view.activity.WantActivity;

public class MineFragment extends Fragment implements MineContract.IView {

    @BindView(R.id.gv_lattice)
    GridView mgridlayout;
    @BindView(R.id.circleimageview)
    CircleImageView circleimageview;
    @BindView(R.id.tv_name)
    TextView tvName;

    private MinePresenter minePresenter;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        minePresenter = new MinePresenter(this);
        minePresenter.getUserInfo();
        minePresenter.getGridData();
    }

    @OnClick({R.id.bt_order, R.id.bt_likes, R.id.bt_card, R.id.circleimageview, R.id.bt_notice, R.id.bt_setting})
    public void onClick(View view) {
        Log.d("MineFragment", "onClick:" + view.getId());
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt_order:
                intent = new Intent(getActivity(), ParticipateListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_likes:
                intent = new Intent(getActivity(), WantActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_card:
                ToastUtils.show(getActivity(), R.string.not_open, true);
                break;
            case R.id.circleimageview:
                intent = new Intent(getActivity(), PersonalInformationActivity.class);
                startActivityForResult(intent, Constant.INFO_SETTING);
                break;
            case R.id.bt_notice:
                break;
            case R.id.bt_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, Constant.INFO_SETTING);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.INFO_SETTING) {
            minePresenter.getUserInfo();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        minePresenter.unsubscrib();
    }

    //from MineContract.IView
    @Override
    public void showGrid(int[] icon, String[] iconName) {
        data_list = new ArrayList<>();
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("icon", icon[i]);
            map.put("name", iconName[i]);
            data_list.add(map);
        }
        GridLayoutManager gm = new GridLayoutManager(getActivity(), 3);
        String[] from = {"icon", "name"};
        int[] to = {R.id.iv_icon, R.id.tv_name};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.item_lattice, from, to);
        mgridlayout.setAdapter(sim_adapter);
        mgridlayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: {
                        Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1: {
                        Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 2: {
                        Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 3: {
                        Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onUserInfoGet(UserInfo userInfo) {
        if (userInfo != null) {
            Glide.with(getActivity()).load(userInfo.getAvatar()).error(R.drawable.icon_avantar)
                    .into(circleimageview);
            tvName.setText(userInfo.getName());
        }
    }

    //from MineContract.IView
    @Override
    public void showError(String err) {
        ToastUtils.show(getActivity(), err, true);
    }

    //from MineContract.IView
    @Override
    public void dealWithNewestNotice(int number) {

    }

}
