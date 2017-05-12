package milai.meishipintu.com.faxianlite.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.DialogUtils;
import milai.meishipintu.com.faxianlite.Tool.Immersive;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.view.fargment.DiscoverFragment;
import milai.meishipintu.com.faxianlite.view.fargment.MineFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Discover-MainActivity";

    private static final int REQUEST_STORAGE_PERMISSION = 100;
    private static final int REQUEST_PHONE_PERMISSION = 200;
    private static final int REQUEST_LOCATION_PERMISSION = 300;


    @BindView(R.id.fram)
    FrameLayout frame;
    @BindView(R.id.iv_recommend)
    ImageView ivRecommend;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;

    private long timeLast = 0l;

    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Immersive.immersive(0xff212121, this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (DiscoverApplication.getUser() == null) {
            //未登录
            startActivity(new Intent(MainActivity.this, LoginOrLoginActivity.class));
        } else {
            //设置Alias
            JPushInterface.setAlias(this, DiscoverApplication.getUser().getUid(), null);
        }
        //申请权限
        phoneStateWapper();
        storageWapper();
        locationWapper();

        //生成fragment
        discoverFragment = new DiscoverFragment();
        mineFragment = new MineFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fram, discoverFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
        ivMine.setEnabled(false);
    }

    //定位权限包装类
    private void locationWapper() {
        int hasLoactionPermission = ContextCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_FINE_LOCATION);
        if (hasLoactionPermission != PackageManager.PERMISSION_GRANTED) {       //未授权
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this
                    , Manifest.permission.ACCESS_FINE_LOCATION)) {                            //系统申请权限框不再弹出
                Log.i("test", "dialog show ," + System.currentTimeMillis());
                DialogUtils.showCustomDialog(this, "本应用需要获取位置权限"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this
                                        ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                                        , REQUEST_LOCATION_PERMISSION);
                                dialog.dismiss();
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest
                    .permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        }
    }

    //存储权限包装类
    private void storageWapper() {
        int hasLoactionPermission = ContextCompat.checkSelfPermission(this
                , Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasLoactionPermission != PackageManager.PERMISSION_GRANTED) {       //未授权
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE)) {                            //系统申请权限框不再弹出
                Log.i("test", "dialog show ," + System.currentTimeMillis());
                DialogUtils.showCustomDialog(this, "本应用需要读写内存权限"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this
                                        ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                                        , REQUEST_STORAGE_PERMISSION);
                                dialog.dismiss();
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest
                    .permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
            return;
        }
    }

    //手机状态权限包装类
    private void phoneStateWapper() {
        int hasLoactionPermission = ContextCompat.checkSelfPermission(this
                , Manifest.permission.READ_PHONE_STATE);
        if (hasLoactionPermission != PackageManager.PERMISSION_GRANTED) {       //未授权
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this
                    , Manifest.permission.READ_PHONE_STATE)) {                            //系统申请权限框不再弹出
                Log.i("test", "dialog show ," + System.currentTimeMillis());
                DialogUtils.showCustomDialog(this, "本应用需要获取手机状态权限"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this
                                        ,new String[]{Manifest.permission.READ_PHONE_STATE}
                                        , REQUEST_PHONE_PERMISSION);
                                dialog.dismiss();
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest
                    .permission.READ_PHONE_STATE}, REQUEST_PHONE_PERMISSION);
            return;
        }
    }

    //再次启动时不调用onCreate,而是从onNewIntent
    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "intent:" + intent.getIntExtra("type", -1));
        int type = intent.getIntExtra("type", -1);
        if (type == Constant.LOGIN_SUCCESS) {
            JPushInterface.setAlias(this, DiscoverApplication.getUser().getUid(), null);
        }
        if (type == Constant.LOGOUT_SUCCESS) {
            JPushInterface.setAlias(this, "", null);
            //重启登录界面
            startActivity(new Intent(MainActivity.this, LoginOrLoginActivity.class));
        } else if (type == Constant.GETOUT) {
            //退出
            this.finish();
        }
    }

    @OnClick({R.id.recommend, R.id.mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recommend:
                if (!discoverFragment.isVisible()) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.show(discoverFragment);
                    transaction.hide(mineFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                    transaction.commit();
                    ivMine.setEnabled(false);
                    tvMine.setEnabled(false);
                    ivRecommend.setEnabled(true);
                    tvRecommend.setEnabled(true);
                }
                break;
            case R.id.mine:
                if (!mineFragment.isVisible()) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    if (!mineFragment.isAdded()) {
                        transaction.add(R.id.fram, mineFragment);
                    } else {
                        transaction.show(mineFragment);
                    }
                    transaction.hide(discoverFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                    transaction.commit();
                    ivMine.setEnabled(true);
                    tvMine.setEnabled(true);
                    ivRecommend.setEnabled(false);
                    tvRecommend.setEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //不允许返回
        long timeNow = System.currentTimeMillis();
        if (timeNow - timeLast < 2000) {
            this.finish();
        } else {
            //两次点击超过2秒则不视为退出
            ToastUtils.show(this, R.string.exit, true);
            timeLast = timeNow;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
            , @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //拒绝授权
                    Toast.makeText(this, "无读写内存卡权限，无法进行下载任务,请在系统设置中增加应用的相应授权", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case REQUEST_PHONE_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //拒绝授权
                    Toast.makeText(this, "无获取手机状态权限，无法实现部分功能，请在系统设置中增加应用的相应授权", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //拒绝授权
                    Toast.makeText(this, "无获取位置权限，无法实现定位操作，请在系统设置中增加应用的相应授权", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }
}

