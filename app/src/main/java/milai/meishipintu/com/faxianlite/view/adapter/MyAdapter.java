package milai.meishipintu.com.faxianlite.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Recommend;
import milai.meishipintu.com.faxianlite.view.activity.DetailsActivity;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Recommend> data = new ArrayList<>();
    private Context context;
    private Picasso picasso;

    public MyAdapter(Context context, List<Recommend> list) {
        this.data = list;
        this.context = context;
        picasso = Picasso.with(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recomment, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Recommend recommend = data.get(position);
        final MyViewHolder MyViewHolder = holder;
        picasso.load(recommend.getLogo()).into(MyViewHolder.ivMainimaget);//加载网络图片
        MyViewHolder.itemView.setTag(recommend.getId());
        MyViewHolder.title.setText(data.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //Intent传递参数
                intent.putExtra("id", position);
                intent.setClass(context, DetailsActivity.class);
                context.startActivity(intent);
                Toast.makeText(v.getContext(),  position+"" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();


    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_mainimaget)
        ImageView ivMainimaget;
        @Bind(R.id.rl)
        RelativeLayout rl;
        @Bind(R.id.tv_title)
        TextView title;
        @Bind(R.id.designer)
        TextView designer;
        @Bind(R.id.name)
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
