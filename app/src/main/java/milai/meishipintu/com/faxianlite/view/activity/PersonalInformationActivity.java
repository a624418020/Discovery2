package milai.meishipintu.com.faxianlite.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ChooseHeadViewDialog;
import milai.meishipintu.com.faxianlite.Tool.CircleImageView;
import milai.meishipintu.com.faxianlite.Tool.DialogUtils;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.Tool.UriUtils;
import milai.meishipintu.com.faxianlite.constract.PersonalInfoContract;
import milai.meishipintu.com.faxianlite.model.PreferrenceHepler;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.presenter.PersonalInfoPresenter;

import static java.security.AccessController.getContext;
import static milai.meishipintu.com.faxianlite.Constant.CHOOSE_PICTURE;
import static milai.meishipintu.com.faxianlite.Constant.CROP_SMALL_PICTURE;
import static milai.meishipintu.com.faxianlite.Constant.INFO_SETTING;
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
    private File tempFile;          //保存照片的临时文件
    private Uri photoURI;           //保存照片路径的content：//格式的uri地址
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
        // 指定照片保存路径（SD卡），avator.jpg为一个临时文件，每次拍照后这个图片都会被替换
        tempFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "avator.jpg");
        new ChooseHeadViewDialog(this, R.style.CustomDialog, new ChooseHeadViewDialog.OnItemClickListener() {
            @Override
            public void onClickCamera(View view, Dialog dialog) {
                dialog.dismiss();
                cameraWapper();                             //申请相机权限
            }

            @Override
            public void onClickAlbum(View view, Dialog dialog) {
                dialog.dismiss();
                //调用相册
                Intent intent = Intent.createChooser(new Intent()
                        .setAction(Intent.ACTION_GET_CONTENT).setType("image/*"), "选择相册");
                startActivityForResult(intent, CHOOSE_PICTURE);
            }
        }).show();

    }

    //申请相机权限的包装方法
    private void cameraWapper() {
        int hasStoragePermission = ContextCompat.checkSelfPermission(this
                , Manifest.permission.CAMERA);
        if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {        //未授权
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this
                    , android.Manifest.permission.CAMERA)) {                    //系统申请权限框不再弹出
                DialogUtils.showCustomDialog(this, "本应用需要获取使用相机权限"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(PersonalInformationActivity.this
                                        , new String[]{android.Manifest.permission.CAMERA}
                                        , Constant.REQUEST_CAMERA_PERMISSION);
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                return;
            }
            //系统框弹出时直接申请
            ActivityCompat.requestPermissions(PersonalInformationActivity.this,new String[]{android
                    .Manifest.permission.CAMERA},Constant.REQUEST_CAMERA_PERMISSION);
            return;
        }

        //调用相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < 24) {
            //7.0以前版本
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
            startActivityForResult(intent, TAKE_PICTURE);
        } else {
            /* getUriForFile(Context context, String authority, File file):此处的authority需要和manifest里面保持一致。
                photoURI打印结果：content://cn.lovexiaoai.myapp.fileprovider/camera_photos/Pictures/Screenshots/testImg.png 。
                这里的camera_photos:对应filepaths.xml中的name
            */
            photoURI = FileProvider.getUriForFile(this, "milai.meishipintu.com.faxianlite.fileprovider", tempFile);

            /* 这句要记得写：这是申请权限，之前因为没有添加这个，打开裁剪页面时，一直提示“无法修改低于50*50像素的图片”，
                开始还以为是图片的问题呢，结果发现是因为没有添加FLAG_GRANT_READ_URI_PERMISSION。
                如果关联了源码，点开FileProvider的getUriForFile()看看（下面有），注释就写着需要添加权限。
            */
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            //获取相机元图片，不经过压缩，并保存在uir位置
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            //调用系统相机
            startActivityForResult(intent, TAKE_PICTURE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //选照片或拍照返回
            switch (requestCode) {
                case TAKE_PICTURE:
                    //拍照返回
                    if (Build.VERSION.SDK_INT < 24) {
                        startPhotoCrop(Uri.fromFile(tempFile)); // 开始对图片进行裁剪处理
                    } else {
                        startPhotoCrop(photoURI);
                    }
                    break;
                case CHOOSE_PICTURE:
                    //相册选择图片返回
                    Uri fileUri = data.getData();
                    //对返回的Uri地址进行转换
//                    fileUri = UriUtils.convertUri(fileUri, this);
                    startPhotoCrop(fileUri); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    //裁剪图片返回
                    Log.d("personalActivity", "back from crop," + data);
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
                case INFO_SETTING:
                    //刷新页面
                    UserInfo userInfo = (UserInfo) data.getExtras().get("userInfo");
                    refreshUI(userInfo);
                    break;
            }
        }
    }

    @Override
    public void refreshUI(UserInfo userInfo) {
        Log.d("PersonalInfo", "UserInfo now:" + userInfo);
        //修改储存的userInfo
        DiscoverApplication.setUser(userInfo);
        PreferrenceHepler.saveUser(userInfo);
        onPersonalInfoGet(userInfo);
    }

    //将图片显示并保存
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            circleimageview.setImageBitmap(photo);
            //将剪切后图片保存在缓存文件中
            UriUtils.saveBitmap(photo, tempFile);
            //更新用户头像信息
            mPresenter.saveAvatar(tempFile, userInfo.getUid());
        }
    }

    //裁剪图片Uri
    protected void startPhotoCrop(Uri uri) {
        if (uri == null) {
            Log.d("tag", "The uri is not exist.");
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 120);
        intent.putExtra("outputY", 120);
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

    //授权回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
            , @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.REQUEST_CAMERA_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权通过
                    cameraWapper();
                } else {
                    //拒绝授权
                    Toast.makeText(this, "无相机使用权限，无法进行拍摄，请在系统设置中增加应用的相应授权"
                            , Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
