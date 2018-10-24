package soexample.umeng.com.gouwuche.goshop.view;

import java.util.List;

import soexample.umeng.com.gouwuche.bean.MessageBean;
import soexample.umeng.com.gouwuche.bean.Product;
import soexample.umeng.com.gouwuche.bean.ShopBean;

/**
 * Created by Shinelon on 2018/10/23.
 */

public interface Iview {
    void success(MessageBean<List<ShopBean<List<Product>>>> list);
    void failed(Exception e);
}
