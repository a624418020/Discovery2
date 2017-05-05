package milai.meishipintu.com.faxianlite.view.fargment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.CircleImageView;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.MineContract;
import milai.meishipintu.com.faxianlite.presenter.MinePresenter;
import milai.meishipintu.com.faxianlite.view.activity.ParticipateListActivity;
import milai.meishipintu.com.faxianlite.view.activity.SiteActivity;
import milai.meishipintu.com.faxianlite.view.activity.WantActivity;

import static android.app.Activity.RESULT_OK;


public class MineFragment extends Fragment implements MineContract.IView {
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;


    @BindView(R.id.gv_lattice)
    GridView mgridlayout;
    @BindView(R.id.circleimageview)
    CircleImageView civ;

    private SimpleAdapter sim_adapter;

    private Intent intent;
    protected static Uri tempUri;
    private MinePresenter minePresenter;
    private List<Map<String, Object>> data_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        minePresenter = new MinePresenter(this);
        minePresenter.getGridData();
        intent = new Intent();
    }


    public void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case 1: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            civ.setImageBitmap(photo);
        }
    }

    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    @OnClick({R.id.bt_order, R.id.bt_likes, R.id.bt_card, R.id.circleimageview,R.id.bt_notice,R.id.bt_site})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_order:
//                intent.putExtra();
                intent.setClass(getActivity(), ParticipateListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_likes:
                // intent.putExtra();
                intent.setClass(getActivity(), WantActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_card:
                break;
            case R.id.circleimageview:
                showChoosePicDialog();
                break;
            case R.id.bt_notice:
                break;
            case R.id.bt_site:
                intent.setClass(getActivity(), SiteActivity.class);
                startActivity(intent);
                break;
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

    //from MineContract.IView
    @Override
    public void showError(String err) {
        ToastUtils.show(getActivity(), err, true);
    }

    //from MineContract.IView
    @Override
    public void dealWithNewestNotice(int number) {

    }

    //from MineContract.IView
    @Override
    public void setHeadportrait(String url) {

    }
}
