package soexample.umeng.com.gouwuche.bean;

import com.google.gson.Gson;

/**
 * Created by Shinelon on 2018/10/23.
 */

public class Product {



    private float bargainPrice;
    private String createtime;
    private String detailUrl;
    private String images;
    private int num;
    private int pid;
    private float price;
    private int pscid;
    private int selected;
    private int sellerid;
    private String subhead;
    private String title;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public static Product objectFromData(String str) {

        return new Gson().fromJson(str, Product.class);
    }

    public float getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(float bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPscid() {
        return pscid;
    }

    public void setPscid(int pscid) {
        this.pscid = pscid;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
