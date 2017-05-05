package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;

public class SiteActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        ButterKnife.bind(this);
        intent=new Intent();
    }

    @OnClick({R.id.bt_return, R.id.bt_site, R.id.bt_presonalinformation, R.id.bt_cache, R.id.bt_aboutus, R.id.bt_comments, R.id.bt_abort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                break;
            case R.id.bt_site:
                intent.setClass(this, PersonalInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_presonalinformation:
                break;
            case R.id.bt_cache:
                break;
            case R.id.bt_aboutus:
                break;
            case R.id.bt_comments:
                break;
            case R.id.bt_abort:
                break;
        }
    }
}
