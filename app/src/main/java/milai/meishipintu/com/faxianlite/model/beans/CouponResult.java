package milai.meishipintu.com.faxianlite.model.beans;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class CouponResult {

    private int status;
    private String msg;
    private Coupon data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return msg;
    }

    public void setInfo(String info) {
        this.msg = info;
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
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
