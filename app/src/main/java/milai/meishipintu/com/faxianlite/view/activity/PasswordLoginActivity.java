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
import milai.meishipintu.com.faxianlite.constract.PasswordLoginContract;
import milai.meishipintu.com.faxianlite.presenter.PasswordLoginPresenter;

public class PasswordLoginActivity extends AppCompatActivity implements PasswordLoginContract.IView{

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_password)
    EditText etPassword;

    private PasswordLoginContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);
        ButterKnife.bind(this);
        mPresenter = new PasswordLoginPresenter(this);
    }

    @OnClick({R.id.bt_return, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                this.finish();
                break;
            case R.id.bt_login:
                String tel = etTel.getText().toString();
                String password = etPassword.getText().toString();
                if (StringUtils.isNullOrEmpty(new String[]{tel, password})) {
                    showError(getResources().getString(R.string.err_input));
                } else if (!StringUtils.isTel(tel)) {
                    showError(getResources().getString(R.string.err_tel));
                } else {
                    mPresenter.login(tel,password);
                }
                break;
        }
    }

    @Override
    public void showError(String err) {
        ToastUtils.show(this, err, true);
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(PasswordLoginActivity.this, MainActivity.class);
        intent.putExtra("type", Constant.LOGIN_SUCCESS);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }
}
