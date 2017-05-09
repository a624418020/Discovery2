package milai.meishipintu.com.faxianlite.Tool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;


import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;

/**
 * Created by Administrator on 2016/9/28.
 */

public class ChooseHeadViewDialog extends Dialog {

    OnItemClickListener listener;

    public ChooseHeadViewDialog(Context context, OnItemClickListener listener) {
        super(context);
        this.listener = listener;
    }

    public ChooseHeadViewDialog(Context context, int themeResId, OnItemClickListener listener) {
        super(context, themeResId);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_view_select);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    @OnClick({R.id.tv_from_camera, R.id.tv_from_album})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_from_camera:
                listener.onClickCamera(view, this);
                break;
            case R.id.tv_from_album:
                listener.onClickAlbum(view, this);
                break;
        }
    }

    public interface OnItemClickListener {
        void onClickCamera(View view, Dialog dialog);

        void onClickAlbum(View view, Dialog dialog);
    }

}
