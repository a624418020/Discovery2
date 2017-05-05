package milai.meishipintu.com.faxianlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import milai.meishipintu.com.faxianlite.DiscoverApplication;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.PreferrenceHepler;

public class StartActivity extends AppCompatActivity {

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(0, 3000);
    }

    //handler设为内部静态类，防止handler持有activity对象导致内存泄漏
    private static class MyHandler extends Handler {
        private final WeakReference<StartActivity> reference;

        MyHandler(StartActivity activity){
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                StartActivity activity = reference.get();
                if (activity != null) {
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                }
            }
        }
    }
}
