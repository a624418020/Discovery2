package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.constract.LoginContract;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import milai.meishipintu.com.faxianlite.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.IView{

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
    public void onLoginSuccess(UserInfo userInfo) {
        Intent intent = new Intent();
        intent.putExtra("userInfo", userInfo);
        setResult(Constant.LOGIN_SUCCESS, intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrib();
    }
}
