package milai.meishipintu.com.faxianlite.Tool;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class BetterRecyclerView extends RecyclerView {

    private float slideDistance = 0; // 滑动的距离
    private int scrollY = 0; // y轴当前的位置
    private int totalPage = 5; // 总页数
    private int shortestDistance; // 超过此距离的滑动才有效
    private int currentPage = 1; // 当前页

    public BetterRecyclerView(Context context) {
        this(context, null);
    }

    public BetterRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BetterRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        shortestDistance = getMeasuredHeight()/ 3;
    }

    /*
      * 0: 停止滚动且手指移开; 1: 开始滚动; 2: 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
      */
    private int scrollState = 0; // 滚动状态

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            Log.i("aaa","down");
        }
        if(e.getAction() == MotionEvent.ACTION_MOVE) {
            //当手指按下的时候
            Log.i("aaa","move");
        }        if(e.getAction() == MotionEvent.ACTION_UP) {
            //当手指按下的时候
            Log.i("aaa","up");
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void onScrollStateChanged(int state) {
        Log.i("aaa","aaa");
        //获取开始滚动时所在页面的index
        switch (state) {
            case 2:
                scrollState = 2;
                break;
            case 1:
                scrollState = 1;
                break;
            case 0:
                if (slideDistance == 0) {
                    break;
                }
                scrollState = 0;
                if (slideDistance < 0) { // 上页
                    currentPage = (int) Math.ceil(scrollY / getHeight());
                    if (currentPage * getHeight() - scrollY < shortestDistance) {
                        currentPage += 1;
                    }
                } else { // 下页
                    currentPage = (int) Math.ceil((double)scrollY / getHeight())+1;
                    if (currentPage <= totalPage) {
                        Log.i("",scrollY+"---"+currentPage+"----"+getHeight()+"----"+shortestDistance);
                        if (scrollY - (currentPage - 2) * getHeight() < shortestDistance) {
                            // 如果这一页滑出距离不足，则定位到前一页
                            currentPage -= 1;
                        }
                    } else {

                        currentPage = totalPage;
                    }
                }
                // 执行自动滚动
                smoothScrollBy( 0,(int) ((currentPage - 1) * getHeight() - scrollY));
                slideDistance = 0;
                break;
        }
        super.onScrollStateChanged(state);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        scrollY +=  dy;
        if (scrollState == 1) {
            slideDistance += dy;
            Log.i("aaa",slideDistance+"");

        }

        super.onScrolled(dx, dy);
    }

}

