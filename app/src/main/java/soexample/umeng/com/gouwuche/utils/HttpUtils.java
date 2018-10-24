package soexample.umeng.com.gouwuche.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import soexample.umeng.com.gouwuche.inat.INatCallBack;

/**
 * Created by Shinelon on 2018/10/23.
 */

public class HttpUtils {
    private static volatile HttpUtils insenter;
    private OkHttpClient client;
    private Handler handler=new Handler(Looper.getMainLooper());
    private HttpUtils(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client=new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    public static HttpUtils getInsenter(){
        if (insenter==null){
            synchronized (HttpUtils.class){
                if (null==insenter){
                    insenter=new HttpUtils();
                }
            }
        }
        return insenter;
    }

    public void get(String url, final INatCallBack callBack, final Type type){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      callBack.failer(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(result, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.success(o);
                    }
                });
            }
        });
    }
}
