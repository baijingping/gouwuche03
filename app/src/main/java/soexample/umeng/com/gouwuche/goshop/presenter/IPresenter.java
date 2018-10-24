package soexample.umeng.com.gouwuche.goshop.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import soexample.umeng.com.gouwuche.bean.MessageBean;
import soexample.umeng.com.gouwuche.bean.Product;
import soexample.umeng.com.gouwuche.bean.ShopBean;
import soexample.umeng.com.gouwuche.goshop.model.IModel;
import soexample.umeng.com.gouwuche.goshop.view.Iview;
import soexample.umeng.com.gouwuche.inat.INatCallBack;

/**
 * Created by Shinelon on 2018/10/23.
 */

public class IPresenter {
    private Iview iv;
    private IModel model;
    public void attach(Iview iv){
        this.iv=iv;
        model=new IModel();
    }

    public void getData(){
        String url = "http://www.zhaoapi.cn/product/getCarts?uid=1538";
        Type type=new TypeToken<MessageBean<List<ShopBean<List<Product>>>>>(){}.getType();
        model.getData(url, new INatCallBack() {
            @Override
            public void success(Object o) {
                MessageBean<List<ShopBean<List<Product>>>> data= (MessageBean<List<ShopBean<List<Product>>>>) o;
                iv.success(data);
            }

            @Override
            public void failer(Exception e) {
                iv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (iv!=null){
            iv=null;
        }
    }
}
