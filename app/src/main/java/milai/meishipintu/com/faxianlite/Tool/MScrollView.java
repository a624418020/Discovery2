package milai.meishipintu.com.faxianlite.Tool;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class MScrollView extends ScrollView {


    private MScrollView.OnScrollChangedCallback mOnScrollChangedCallback;
    public MScrollView(Context context) {
        super(context);
    }

    public MScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int w, int h, int oldw, int oldh) {
        super.onScrollChanged(w,h,oldw,oldh);
        if (oldh < h && ((h - oldh) > 15)) {// 向上
            Log.e("wangly", "距离："+(oldh < h) +"---"+(h - oldh));
            Log.d("TAG","向上滑动");
            mOnScrollChangedCallback.onScroll(false);

        }  else if (oldh > h && (oldh - h) > 15) {// 向下
            Log.e("wangly", "距离："+(oldh > h) +"---"+(oldh - h));
            Log.d("TAG"," 向下滑动");
            mOnScrollChangedCallback.onScroll(true);
        }

        if (getChildAt(0).getMeasuredHeight() <= getScrollY() + getHeight()) {
            mOnScrollChangedCallback.onReachBottom();
        }
    }
    public MScrollView.OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(
         final MScrollView.OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }


    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public interface OnScrollChangedCallback {
        void onScroll(boolean show);

        void onReachBottom();
    }

}
