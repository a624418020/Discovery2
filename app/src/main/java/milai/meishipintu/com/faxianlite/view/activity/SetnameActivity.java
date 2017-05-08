package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.StringUtils;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;
import milai.meishipintu.com.faxianlite.model.Retrofit.NetApi;
import milai.meishipintu.com.faxianlite.model.beans.UserInfo;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SetnameActivity extends AppCompatActivity {

    private String name;
    private String newName;
    private String uid;
    private Subscription subscription;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.bt_save)
    TextView tvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setname);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        tvTitle.setText(R.string.change_nick_name);
        UserInfo userInfo = (UserInfo) getIntent().getExtras().get("userInfo");
        name = userInfo.getName();
        uid = userInfo.getUid();
        Log.d("setName:", "name:" + name + ", uid:" + uid);
        if (!StringUtils.isNullOrEmpty(name)) {
            etName.setText(name);
        }
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                newName = s.toString();
                if (!StringUtils.isNullOrEmpty(newName) && !newName.equals(name)) {
                    //新输入不为空切与就输入不一致
                    tvSave.setEnabled(true);
                } else {
                    tvSave.setEnabled(false);
                }
            }
        });
    }


    @OnClick({R.id.bt_return, R.id.bt_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_return:
                onBackPressed();
                break;
            case R.id.bt_save:
                if (tvSave.isEnabled()) {
                    subscription = NetApi.getInstance().updateUserInfo(uid, newName, null)
                            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                            .subscribe(new Subscriber<UserInfo>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {
                                    ToastUtils.show(SetnameActivity.this, "修改昵称失败，请稍后重试", true);
                                }

                                @Override
                                public void onNext(UserInfo userInfo) {
                                    Intent intent = new Intent();
                                    intent.putExtra("userInfo", userInfo);
                                    setResult(RESULT_OK, intent);
                                    SetnameActivity.this.finish();
                                }
                            });
                }
                break;
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
