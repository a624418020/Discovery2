package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.ModifyPhoneContract;

public class ModifyPhoneActivity extends AppCompatActivity implements ModifyPhoneContract.IView{

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.bt_verify)
    TextView btVerify;

    private ModifyPhoneContract.IPresenter mPresenter;
    private String tel;
    private String verifyGet;
    private String verifyInput;
    private int timeRemain;
    private MyHandler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);
        ButterKnife.bind(this);
        initView();

    }

    private TextWatcher wather = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            tel = etTel.getText().toString();
            verifyInput = etPassword.getText().toString();
            if (StringUtils.isTel(tel) && !StringUtils.isNullOrEmpty(verifyInput)) {
                tvConfirm.setEnabled(true);
            } else {
                tvConfirm.setEnabled(false);
            }
        }
    };

    private void  initView(){
        tvTitle.setText("修改手机号码");
        etTel.setHint("请输入新手机号码");
        etPassword.setHint("请输入验证码");
        tvConfirm.setText("修改");
        btVerify.setVisibility(View.VISIBLE);
        tvConfirm.addTextChangedListener(wather);
    }

    @OnClick({R.id.bt_return, R.id.bt_verify, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                finish();
                break;
            case R.id.bt_verify:
                if (StringUtils.isTel(tel)) {
                    if (btVerify.isEnabled()) {
                        timeRemain = 60;
                        btVerify.setEnabled(false);
                        mPresenter.getVerifyCode(tel);
                    }
                } else {
                    showError(getResources().getString(R.string.err_tel));
                }
                break;
            case R.id.bt_login:
                if (tvConfirm.isEnabled()) {
                    if (!verifyInput.equals(verifyGet)) {
                        showError(getResources().getString(R.string.err_vcode));
                    } else {
                        mPresenter.modifyPhone(DiscoverApplication.getUser().getUid(), tel, verifyInput);
                    }
                } else {
                    showError(getResources().getString(R.string.err_input));
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void showError(String err) {
        ToastUtils.show(this, err, true);
    }

    @Override
    public void onVerifyCodeGet(String verifyCode) {
        verifyGet = verifyCode;
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void onModifyPhoneSucess() {
        
    }

    //handler设为内部静态类，防止handler持有activity对象导致内存泄漏
    private static class MyHandler extends Handler {
        private final WeakReference<ModifyPhoneActivity> reference;

        MyHandler(ModifyPhoneActivity activity){
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ModifyPhoneActivity activity = reference.get();
            if (activity.timeRemain > 0) {
                this.sendEmptyMessageDelayed(0, 1000);
                activity.btVerify.setText(""+(--activity.timeRemain)+"s");
            } else {
                activity.btVerify.setEnabled(true);
                activity.btVerify.setText(R.string.getVerifyCode);
            }
        }
    }
}
