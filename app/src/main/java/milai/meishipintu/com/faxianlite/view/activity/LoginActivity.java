package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    private String verifyCode = null;
    private LoginContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
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
    }

    //from LoginContract.IView
    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("type", Constant.LOGIN_SUCCESS);
        startActivity(intent);
        this.finish();
    }


    @OnClick({R.id.bt_verify, R.id.bt_login, R.id.bt_passwordlogin, R.id.bt_weichat_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_verify:
                String tel = etTel.getText().toString();
                if (StringUtils.isTel(tel)) {
                    mPresenter.getVCode(tel);
                } else {
                    showError(getResources().getString(R.string.err_tel));
                }
                break;
            case R.id.bt_login:
                String mobile = etTel.getText().toString();
                String vCode = etVerify.getText().toString();
                if (StringUtils.isNullOrEmpty(new String[]{mobile, vCode})) {
                    showError(getResources().getString(R.string.err_input));
                } else if (!vCode.equals(verifyCode)) {
                    showError(getResources().getString(R.string.err_vcode));
                } else {
                    mPresenter.login(mobile, vCode);
                }
                break;
            case R.id.bt_passwordlogin:
                startActivity(new Intent(LoginActivity.this, PasswordLoginActivity.class));
                break;
            case R.id.bt_weichat_login:
                mPresenter.loginWeiChat();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }
}
