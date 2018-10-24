package soexample.umeng.com.gouwuche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.gouwuche.adapter.ProductAdapter;
import soexample.umeng.com.gouwuche.adapter.RAdapter;
import soexample.umeng.com.gouwuche.bean.MessageBean;
import soexample.umeng.com.gouwuche.bean.Product;
import soexample.umeng.com.gouwuche.bean.ShopBean;
import soexample.umeng.com.gouwuche.goshop.presenter.IPresenter;
import soexample.umeng.com.gouwuche.goshop.view.Iview;

public class MainActivity extends AppCompatActivity implements Iview {

    private TextView txtEditFinish;
    private CheckBox cbTotal;
    private TextView txtPrice;
    private Button btnCalu;
    private RecyclerView rvShoper;
    private List<ShopBean<List<Product>>> productList;
    private RAdapter adapter;
    private IPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        productList=new ArrayList<>();
        adapter=new RAdapter(this,productList);

        adapter.setonShopperClickListener(new RAdapter.onShopperClickListener() {
            @Override
            public void onShopperClick(int position, boolean isCheck) {
                if (!isCheck){
                    cbTotal.setChecked(false);
                }else {
                    boolean isAllShopperChecked=true;
                    for (ShopBean<List<Product>> listShopBean : productList) {
                        if (!listShopBean.isChecked()){
                            isAllShopperChecked=false;
                            break;
                        }
                    }
                    cbTotal.setChecked(isAllShopperChecked);
                }
                calculatePrice();
            }
        });

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rvShoper.setLayoutManager(layoutManager);
        rvShoper.setAdapter(adapter);

        cbTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = cbTotal.isChecked();
                for (ShopBean<List<Product>> listShopBean : productList) {
                    listShopBean.setChecked(checked);
                    List<Product> products = listShopBean.getList();
                    for (Product product : products) {
                        product.setChecked(checked);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnAddDecreaseProductListener(new ProductAdapter.OnAddDecreaseProductListener() {
            @Override
            public void onChange(int position, int num) {
                calculatePrice();
            }
        });
    }

    private void initView() {
        txtEditFinish = findViewById(R.id.txt_edit_or_finish);
        cbTotal = findViewById(R.id.cb_total_select);
        txtPrice = findViewById(R.id.txt_total_price);
        btnCalu = findViewById(R.id.btn_calu);
        rvShoper = findViewById(R.id.rv_shopper);
        presenter=new IPresenter();
        presenter.attach(this);
        presenter.getData();
    }

    @Override
    public void success(MessageBean<List<ShopBean<List<Product>>>> list) {
         if (list!=null){
             List<ShopBean<List<Product>>> data = list.getData();
             if (data!=null){
                 productList.clear();
                 productList.addAll(data);
                 adapter.notifyDataSetChanged();
             }
         }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void calculatePrice() {
        // 遍历商家
        float totalPrice = 0;
        for (ShopBean<List<Product>> listShopper : productList) {
            // 遍历商家的商品
            List<Product> list = listShopper.getList();
            for (Product product : list) {
                // 如果商品被选中
                if (product.isChecked()) {
                    totalPrice += product.getNum() * product.getPrice();
                }
            }
        }

        txtPrice.setText("总价：" + totalPrice);

    }
}
