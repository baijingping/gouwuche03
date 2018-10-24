package soexample.umeng.com.gouwuche.goshop.model;

import java.lang.reflect.Type;

import soexample.umeng.com.gouwuche.inat.INatCallBack;
import soexample.umeng.com.gouwuche.utils.HttpUtils;

/**
 * Created by Shinelon on 2018/10/23.
 */

public class IModel {
    public void getData(String url, INatCallBack callBack, Type type){
        HttpUtils.getInsenter().get(url,callBack,type);
    }
}
