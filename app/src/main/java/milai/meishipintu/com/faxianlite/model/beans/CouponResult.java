package milai.meishipintu.com.faxianlite.model.beans;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class CouponResult {

    private int status;
    private String info;
    private Coupon data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Coupon getCoupon() {
        return data;
    }

    public void setCoupon(Coupon data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CouponResult{" +
                "status=" + status +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
