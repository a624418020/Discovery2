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
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.LoginContract;
import milai.meishipintu.com.faxianlite.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.IView {

    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.bt_verify)
    TextView btVerify;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.bt_login)
    TextView btLogin;

    private String verifyCode = null;
    private LoginContract.IPresenter mPresenter;
    private int remainTime;                 //按键等待时间
    private MyHandler mHandler;
    //记录输入量
    private String tel;
    private String verifyCodeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        mHandler = new MyHandler(this);
        setListener();
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
            verifyCodeInput = etVerify.getText().toString();
            if (StringUtils.isTel(tel) && !StringUtils.isNullOrEmpty(verifyCodeInput)) {
                btLogin.setEnabled(true);
            } else {
                btLogin.setEnabled(false);
            }
        }
    };

    private void setListener() {
        etTel.addTextChangedListener(wather);
        etVerify.addTextChangedListener(wather);
    }

    //from LoginContract.IView
    @Override
    public void showError(String err) {
        ToastUtils.show(this, err, true);
    }

    //from LoginContract.IView
    @Override
    public void onVCodeGet(String VCode) {
        verifyCode = VCode;
        mHandler.sendEmptyMessage(0);
    }

    //from LoginContract.IView
    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("type", Constant.LOGIN_SUCCESS);
        startActivity(intent);
        this.finish();
    }


    @OnClick({R.id.bt_verify, R.id.bt_login, R.id.bt_passwordlogin, R.id.bt_weichat_login, R.id.bt_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.bt_login:
                if (btLogin.isEnabled()) {
                    if (!verifyCodeInput.equals(verifyCode)) {
                        showError(getResources().getString(R.string.err_vcode));
                    } else {
                        mPresenter.login(tel, verifyCodeInput);
                    }
                } else {
                    showError(getResources().getString(R.string.err_input));
                }
                break;
            case R.id.bt_passwordlogin:
                startActivity(new Intent(LoginActivity.this, PasswordLoginActivity.class));
                break;
            case R.id.bt_weichat_login:
                mPresenter.loginWeiChat();
                break;
            case R.id.bt_close:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
        mHandler.removeMessages(0);
    }

    //handler设为内部静态类，防止handler持有activity对象导致内存泄漏
    private static class MyHandler extends Handler {
        private final WeakReference<LoginActivity> reference;

        MyHandler(LoginActivity activity){
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoginActivity activity = reference.get();
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
