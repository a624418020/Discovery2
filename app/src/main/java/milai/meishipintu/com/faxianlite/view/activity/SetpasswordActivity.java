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
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;

public class SetpasswordActivity extends AppCompatActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_password_old)
    EditText etPasswordOld;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_new_password_again)
    EditText etNewPasswordAgain;
    @BindView(R.id.bt_save)
    TextView btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpassword);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        tvTitle.setText("修改密码");
    }


    @OnClick({R.id.bt_return, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                onBackPressed();
                break;
            case R.id.bt_save:
                String passNew = etNewPassword.getText().toString();
                String newPassAgain = etNewPasswordAgain.getText().toString();
                String oldPass = etPasswordOld.getText().toString();
                if (StringUtils.isNullOrEmpty(new String[]{passNew, newPassAgain, oldPass})) {
                    ToastUtils.show(this, R.string.err_input, true);
                } else if (!passNew.equals(newPassAgain)) {
                    ToastUtils.show(this, R.string.newpass_err, true);
                } else {
                    //调用接口
                }
                break;
        }
    }
}
