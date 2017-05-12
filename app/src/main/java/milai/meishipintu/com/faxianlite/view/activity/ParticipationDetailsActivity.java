package milai.meishipintu.com.faxianlite.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milai.meishipintu.com.faxianlite.R;
import milai.meishipintu.com.faxianlite.model.beans.Collection;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class ParticipationDetailsActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.coupon_sn)
    TextView couponSn;
    @BindView(R.id.iv_headlines)
    ImageView ivHeadlines;
    @BindView(R.id.tv_title0)
    TextView tvTitle0;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_People_name)
    TextView tvPeopleName;
    @BindView(R.id.tv_People_phone)
    TextView tvPeoplePhone;
    @BindView(R.id.tv_Ordernumber)
    TextView tvOrdernumber;
    @BindView(R.id.tv_Createtime)
    TextView tvCreatetime;
    @BindView(R.id.iv_zxing)
    ImageView ivZxing;
    private Collection collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_details);
        ButterKnife.bind(this);
        collection = (Collection) getIntent().getSerializableExtra("Collection");
        initView();
        Log.i("collection", collection.toString());

    }

    private void initView() {
        tvTitle.setText("参与详情");
        couponSn.setText(collection.getCoupon_sn());
        tvTitle0.setText(collection.getTitle());
        Glide.with(this).
                load("http://" + collection.getLogo()).
                into(ivHeadlines);//显示到目标View中
        ivZxing.setImageBitmap(zxing());

    }

    private Bitmap zxing() {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(collection.getCoupon_sn(), BarcodeFormat.QR_CODE, 150, 150);
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


    @OnClick(R.id.bt_return)
    public void onClick() {
        finish();
    }
}
