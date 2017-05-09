package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.CanScrollItemView;

/**
 * Created by tangyangkai on 16/6/12.
 */


public class WantAdapter extends RecyclerView.Adapter<WantedViewHolder> implements CanScrollItemView.IonSlidingButtonListener {

    private Context mContext;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private List<String> mDatas = new ArrayList<String>();
    private CanScrollItemView mMenu = null;

    public WantAdapter(Context context) {
        mContext = context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
        for (int i = 0; i < 20; i++) {
            mDatas.add("第"+i+"个测试");
        }
    }

    public void updateData( List<String> mDatas){
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public WantedViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        CanScrollItemView view = (CanScrollItemView) LayoutInflater.from(mContext).inflate(R.layout.item_want_recycler, arg0, false);
        WantedViewHolder holder = new WantedViewHolder(view);
        view.setSlidingButtonListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final WantedViewHolder holder, final int position) {
        holder.textView.setText(mDatas.get(position));
        //设置内容布局的宽为屏幕宽度
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        holder.layout_content.getLayoutParams().width = width;
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }
            }
        });
        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mIDeleteBtnClickListener.onDeleteBtnCilck(v, position);
                mDatas.remove(position);
                WantAdapter.this.notifyItemRemoved(position);
                WantAdapter.this.notifyItemChanged(position, mDatas.size() - position);
            }
        });
    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (CanScrollItemView) view;
    }
    /**
     * 滑动或者点击了Item监听
     * @param canScrollItemView
     */
    @Override
    public void onDownOrMove(CanScrollItemView canScrollItemView) {
        if(menuIsOpen()){
            if(mMenu != canScrollItemView){
                closeMenu();
            }
        }

    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    /**     * 关闭菜单     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    /**     * 判断是否有菜单打开     */
    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        return false;
    }

    public interface IonSlidingViewClickListener {

        void onItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }
}