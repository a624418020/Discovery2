package milai.meishipintu.com.faxianlite.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.Constant;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.ToastUtils;

public class LoginOrLoginActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;

    private long timeLast = 0l;          //记录上一次点击事件，默认0；

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_login);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        ivBack.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator scalex = ObjectAnimator.ofFloat(ivBack, "scaleX", 1.0f, 1.08f);
                scalex.setDuration(3000);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivBack, "scaleY", 1.0f, 1.08f);
                scaleY.setDuration(3000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new DecelerateInterpolator());
                animatorSet.play(scalex).with(scaleY);
                animatorSet.start();
            }
        });
    }

    @OnClick({R.id.bt_register, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                startActivity(new Intent(LoginOrLoginActivity.this, RegisteredActivity.class));
                break;
            case R.id.bt_login:
                startActivity(new Intent(LoginOrLoginActivity.this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //不允许返回
        long timeNow = System.currentTimeMillis();
        if (timeNow - timeLast < 2000) {
            //两次点击小于1秒则退出
            Intent intent = new Intent(LoginOrLoginActivity.this, MainActivity.class);
            intent.putExtra("type", Constant.GETOUT);
            //回到首页退出
            startActivity(intent);
            this.finish();
        } else {
            //两次点击超过1秒则不视为退出
            ToastUtils.show(this, R.string.exit, true);
            timeLast = timeNow;
        }
    }
}
