package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SexActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private int sex;
    private String uid;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("性别");
        UserInfo userInfo = (UserInfo) getIntent().getExtras().get("userInfo");
        sex = userInfo.getSex();
        uid = userInfo.getUid();
    }

    @OnClick({R.id.bt_return, R.id.bt_male, R.id.bt_female})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                onBackPressed();
                break;
            case R.id.bt_male:
                chooseSex(0);
                break;
            case R.id.bt_female:
                chooseSex(1);
                break;
        }
    }

    private void chooseSex(int i) {
        if (i != sex) {
            subscription = NetApi.getInstance().updateUserInfo(uid, null, i).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<UserInfo>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.show(SexActivity.this, "修改性别失败，请稍后重试", true);
                        }

                        @Override
                        public void onNext(UserInfo userInfo) {
                            Intent intent = new Intent();
                            intent.putExtra("userInfo", userInfo);
                            setResult(RESULT_OK, intent);
                            SexActivity.this.finish();
                        }
                    });
        } else {
            //未修改，直接返回
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
