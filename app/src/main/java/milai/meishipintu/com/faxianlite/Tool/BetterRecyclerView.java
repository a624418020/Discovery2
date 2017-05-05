package milai.meishipintu.com.faxianlite.Tool;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class BetterRecyclerView extends RecyclerView {
    private static final int INVALID_POINTER = -1;
    private int mScrollPointerId = INVALID_POINTER;
    private int mInitialTouchX, mInitialTouchY;
    private int mTouchSlop;
    public BetterRecyclerView(Context context) {
        this(context, null);
    }

    public BetterRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BetterRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final ViewConfiguration vc = ViewConfiguration.get(getContext());
        mTouchSlop = vc.getScaledTouchSlop();
    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.i("dy","触发滑动");

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("down","触发down");
                mInitialTouchY = (int) (e.getY() );
                Log.i("mInitialTouchY",mInitialTouchY+"");
                return super.onTouchEvent(e);

            case MotionEvent.ACTION_MOVE: {
                final int y = (int) (e.getY());
                final int dy = y - mInitialTouchY;
                Log.i("mInitialTouchY",mInitialTouchY+"");
                Log.i("dy",dy+"");
                scrollBy(0,dy);
//                final boolean canScrollHorizontally = getLayoutManager().canScrollHorizontally();
//                final boolean canScrollVertically = getLayoutManager().canScrollVertically();
//                if (canScrollHorizontally && Math.abs(dx) > mTouchSlop && (Math.abs(dx) >= Math.abs(dy) || canScrollVertically)) {
//                    Log.i("dy","滚");
//                }

                return super.onTouchEvent(e);
            }

            default:
                return super.onTouchEvent(e);
        }
    }
}

