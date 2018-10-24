package soexample.umeng.com.gouwuche.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.gouwuche.R;
import soexample.umeng.com.gouwuche.bean.Product;
import soexample.umeng.com.gouwuche.bean.ShopBean;

/**
 * Created by Shinelon on 2018/10/23.
 */

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {
     private Context context;
     private List<ShopBean<List<Product>>> list;
     public interface onShopperClickListener{
         void onShopperClick(int position,boolean isCheck);
     }
     private onShopperClickListener listener;
     public void setonShopperClickListener(onShopperClickListener listener){
         this.listener=listener;
     }

    private ProductAdapter.OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(ProductAdapter.OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }

    public RAdapter(Context context, List<ShopBean<List<Product>>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_shopper, null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ShopBean<List<Product>> shopBean = list.get(position);
        holder.txtShopperName.setText(shopBean.getSellerName());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        holder.rvProduct.setLayoutManager(layoutManager);
        final ProductAdapter productAdapter=new ProductAdapter(context,shopBean.getList());

        if (productListener!=null){
            productAdapter.setOnAddDecreaseProductListener(productListener);
        }
        productAdapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                if (!isChecked){
                    shopBean.setChecked(false);
                    listener.onShopperClick(position,false);
                }else {
                    boolean isAllProducSelected=true;
                    for (Product product : shopBean.getList()) {
                        if (!product.isChecked()){
                            isAllProducSelected=false;
                            break;
                        }
                    }
                    shopBean.setChecked(isAllProducSelected);
                    listener.onShopperClick(position,true);
                }
                notifyDataSetChanged();
                productListener.onChange(0,0);
            }
        });
        holder.rvProduct.setAdapter(productAdapter);

        holder.cbSHopper.setOnCheckedChangeListener(null);
        holder.cbSHopper.setChecked(shopBean.isChecked());
        holder.cbSHopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shopBean.setChecked(b);
                List<Product> productList = shopBean.getList();
                for (Product product : productList) {
                    product.setChecked(b);
                }
                productAdapter.notifyDataSetChanged();
                if (listener!=null){
                    listener.onShopperClick(position,b);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbSHopper;
        private TextView txtShopperName;
        private RecyclerView rvProduct;
        public ViewHolder(View itemView) {
            super(itemView);
            cbSHopper = itemView.findViewById(R.id.cb_shopper);
            txtShopperName = itemView.findViewById(R.id.txt_shopper_name);
            rvProduct = itemView.findViewById(R.id.rv_product);
        }
    }
}
