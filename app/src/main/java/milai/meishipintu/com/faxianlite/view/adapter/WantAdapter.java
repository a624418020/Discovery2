package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.Tool.MyRecyclerView;

/**
 * Created by tangyangkai on 16/6/12.
 */


public class WantAdapter extends RecyclerView.Adapter<WantAdapter.MyViewHolder> implements MyRecyclerView.IonSlidingButtonListener {
    private Context mContext;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private List<String> mDatas = new ArrayList<String>();
    private MyRecyclerView mMenu = null;
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
    public void onBindViewHolder(final WantAdapter.MyViewHolder holder, int position) {
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
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_want_recycler, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (MyRecyclerView) view;

    }
    /**
     * 滑动或者点击了Item监听
     * @param myRecyclerView
     */
    @Override
    public void onDownOrMove(MyRecyclerView myRecyclerView) {
        if(menuIsOpen()){
            if(mMenu != myRecyclerView){
                closeMenu();
            }
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public TextView textView;
        public ViewGroup layout_content;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            textView = (TextView) itemView.findViewById(R.id.text);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            ((MyRecyclerView) itemView).setSlidingButtonListener(WantAdapter.this);        }    }
    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    /**     * 删除菜单打开信息接收     */

    /**
     * 滑动或者点击了Item监听
     * @param slidingButtonView
     */

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