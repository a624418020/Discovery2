package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.constract.RegisterContract;

public class RegisteredActivity extends AppCompatActivity implements RegisterContract.IView{

    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_vcode)
    EditText etVcode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_register)
    RelativeLayout btRegister;

    RegisterContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
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
                break;
            case R.id.iv_weichat:
                break;
        }
    }

    //from RegisterContract.IView
    @Override
    public void showError(String err) {

    }

    //from RegisterContract.IView
    @Override
    public void onVCodeGet(String VCode) {

    }

    //from RegisterContract.IView
    @Override
    public void onRegisterSuccess() {

    }

    //from RegisterContract.IView
    @Override
    public void onWeChatLoginSuccess() {

    }
}
