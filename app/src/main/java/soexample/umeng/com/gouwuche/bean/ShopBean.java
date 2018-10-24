package soexample.umeng.com.gouwuche.bean;

/**
 * Created by Shinelon on 2018/10/23.
 */

public class ShopBean<T> {
    private String sellerName;
    private String sellerid;
    private boolean isChecked;
    private T list;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
