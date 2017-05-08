package milai.meishipintu.com.faxianlite.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.CircleImageView;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.PersonalInfoContract;
import milai.meishipintu.com.faxianlite.model.PreferrenceHepler;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.presenter.PersonalInfoPresenter;

import static milai.meishipintu.com.faxianlite.Constant.CHOOSE_PICTURE;
import static milai.meishipintu.com.faxianlite.Constant.CROP_SMALL_PICTURE;
import static milai.meishipintu.com.faxianlite.Constant.TAKE_PICTURE;

public class PersonalInformationActivity extends AppCompatActivity implements PersonalInfoContract.IView{

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.circleimageview)
    CircleImageView circleimageview;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_tel)
    TextView tvTel;

    private PersonalInfoContract.IPresenter mPresenter;
    private Uri tempUri;
    private UserInfo userInfo;      //用户信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
        mPresenter = new PersonalInfoPresenter(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.personal_info);
        mPresenter.getPersonalInfo();
    }

    @OnClick({R.id.bt_return, R.id.circleimageview, R.id.rl_nick_name, R.id.rl_sex, R.id.rl_tel, R.id.rl_pass})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt_return:
                onBackPressed();
                break;
            case R.id.circleimageview:
                showChoosePicDialog();
                break;
            case R.id.rl_nick_name:
                intent=new Intent(this,SetnameActivity.class);
                break;
            case R.id.rl_sex:
                intent=new Intent(this,SexActivity.class);
                break;
            case R.id.rl_tel:
                intent=new Intent(this,ModifyPhoneActivity.class);
                break;
            case R.id.rl_pass:
                intent=new Intent(this,SetpasswordActivity.class);
                break;
        }
        if(intent!=null){
            intent.putExtra("userInfo", userInfo);
            Log.d("PersonalInfo", "userInfo now:" + userInfo.toString());
            startActivityForResult(intent, Constant.INFO_SETTING);
        }
    }

    public void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        if(requestCode == Constant.INFO_SETTING && resultCode == RESULT_OK){
            //刷新页面
            UserInfo userInfo = (UserInfo) data.getExtras().get("userInfo");
            //修改储存的userInfo
            DiscoverApplication.setUser(userInfo);
            PreferrenceHepler.saveUser(userInfo);
            onPersonalInfoGet(userInfo);
        } else if (resultCode == RESULT_OK) {
            //选照片或拍照返回
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
                case R.id.rl_nick_name:
                    // 返回名字
                    break;
            }
        }
    }

    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            circleimageview.setImageBitmap(photo);
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



    //from PersonalInfoContract.IView
    @Override
    public void showError(String err) {
        ToastUtils.show(this,err,true);
    }

    //from PersonalInfoContract.IView
    @Override
    public void onPersonalInfoGet(UserInfo userInfo) {
        if (userInfo != null) {
            this.userInfo = userInfo;
            tvNickName.setText(userInfo.getName());
            tvSex.setText(userInfo.getSex() == 0 ? "男" : "女");
            tvTel.setText(userInfo.getTel());
            if (!StringUtils.isNullOrEmpty(userInfo.getAvatar())) {
                Glide.with(this).load(userInfo.getAvatar())
                        .error(R.drawable.icon_avantar).into(circleimageview);
            }
        } else {
            showError("获取用户信息失败，请稍后再试");
        }
    }
}
