package milai.meishipintu.com.faxianlite.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;

public class ModifyPhoneActivity extends AppCompatActivity {

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

    private String Tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);
        ButterKnife.bind(this);
        initView();

    }
    private void  initView(){
        tvTitle.setText("修改手机号码");
        etPassword.setHint("请输入验证码");
        tvConfirm.setText("修改");
        btVerify.setVisibility(View.VISIBLE);
        Tel = etTel.getText().toString();
    }

    @OnClick({R.id.bt_return, R.id.bt_verify, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                finish();
                break;
            case R.id.bt_verify:

                break;
            case R.id.bt_login:

                break;
        }
    }

}
