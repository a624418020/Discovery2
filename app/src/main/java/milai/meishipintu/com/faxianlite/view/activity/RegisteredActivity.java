package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.Encoder;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.RegisterContract;
import milai.meishipintu.com.faxianlite.presenter.RegisterPresenter;

public class RegisteredActivity extends AppCompatActivity implements RegisterContract.IView{

    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_vcode)
    EditText etVcode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_verify)
    TextView btVerify;
    @BindView(R.id.bt_register)
    TextView btRegist;


    private RegisterContract.IPresenter mPresenter;
    private String verifyCode = null;
    private int remainTime;                 //按键等待时间
    private MyHandler handler;

    //记录输入量
    private String tel;
    private String verifyCodeInput;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        mPresenter = new RegisterPresenter(this);
        handler = new MyHandler(this);
        setlistener();
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
            verifyCodeInput = etVcode.getText().toString();
            password = etPassword.getText().toString();
            if (StringUtils.isTel(tel) && !StringUtils.isNullOrEmpty(new String[]{verifyCodeInput, password})) {
                btRegist.setEnabled(true);
            } else {
                btRegist.setEnabled(false);
            }
        }
    };

    private void setlistener() {
        etTel.addTextChangedListener(wather);
        etVcode.addTextChangedListener(wather);
        etPassword.addTextChangedListener(wather);
    }

    @OnClick({R.id.bt_close, R.id.bt_verify, R.id.bt_register, R.id.iv_weichat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_close:
                this.finish();
                break;
            case R.id.bt_verify:
                if (StringUtils.isTel(tel)) {
                    if (btVerify.isEnabled()) {
                        remainTime = 60;
                        btVerify.setEnabled(false);
                        mPresenter.getVCode(tel);
                    }
                } else {
                    showError(getResources().getString(R.string.err_tel));
                }
                break;
            case R.id.bt_register:
                if (btRegist.isEnabled()) {
                    if (!verifyCodeInput.equals(verifyCode)) {
                        showError(getResources().getString(R.string.err_vcode));
                    } else {
                        mPresenter.register(tel, verifyCodeInput, Encoder.md5(password));
                    }
                } else {
                    showError(getResources().getString(R.string.err_input));
                }
                break;
            case R.id.iv_weichat:
                mPresenter.registerWeiChat();
                break;
        }
    }

    //from RegisterContract.IView
    @Override
    public void showError(String err) {
        ToastUtils.show(this, err, true);
    }

    //from RegisterContract.IView
    @Override
    public void onVCodeGet(String VCode) {
        verifyCode = VCode;
        handler.sendEmptyMessage(0);
    }

    //from RegisterContract.IView
    @Override
    public void onRegisterSuccess() {
        Intent intent = new Intent(RegisteredActivity.this, MainActivity.class);
        intent.putExtra("type", Constant.REGISTER_SUCCESS);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
        handler.removeMessages(0);
    }

    //handler设为内部静态类，防止handler持有activity对象导致内存泄漏
    private static class MyHandler extends Handler {
        private final WeakReference<RegisteredActivity> reference;

        MyHandler(RegisteredActivity activity){
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RegisteredActivity activity = reference.get();
            if (activity.remainTime > 0) {
                this.sendEmptyMessageDelayed(0, 1000);
                activity.btVerify.setText(""+(--activity.remainTime)+"s");
            } else {
                activity.btVerify.setEnabled(true);
                activity.btVerify.setText(R.string.getVerifyCode);
            }
        }
    }
}
