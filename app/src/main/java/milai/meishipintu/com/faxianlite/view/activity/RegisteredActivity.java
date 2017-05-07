package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

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
    @BindView(R.id.bt_register)
    RelativeLayout btRegister;

    private RegisterContract.IPresenter mPresenter;
    private String verifyCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        mPresenter = new RegisterPresenter(this);
        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                verifyCode = null;
            }
        });
    }

    @OnClick({R.id.bt_close, R.id.bt_verify, R.id.bt_register, R.id.iv_weichat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_close:
                this.finish();
                break;
            case R.id.bt_verify:
                String tel = etTel.getText().toString();
                if (StringUtils.isTel(tel)) {
                    mPresenter.getVCode(tel);
                } else {
                    showError(getResources().getString(R.string.err_tel));
                }
                break;
            case R.id.bt_register:
                String mobile = etTel.getText().toString();
                String vCode = etVcode.getText().toString();
                String password = etPassword.getText().toString();
                if (StringUtils.isNullOrEmpty(new String[]{mobile, vCode, password})) {
                    showError(getResources().getString(R.string.err_input));
                } else if (!vCode.equals(verifyCode)) {
                    showError(getResources().getString(R.string.err_vcode));
                } else {
                    mPresenter.register(mobile, vCode, Encoder.md5(password));
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
    }
}
