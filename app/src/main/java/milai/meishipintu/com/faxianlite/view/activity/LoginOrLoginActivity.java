package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.R;

public class LoginOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_register, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                startActivity(new Intent(LoginOrLoginActivity.this, RegisteredActivity.class));
                this.finish();
                break;
            case R.id.bt_login:
                startActivity(new Intent(LoginOrLoginActivity.this, LoginActivity.class));
                this.finish();
                break;
        }
    }
}
