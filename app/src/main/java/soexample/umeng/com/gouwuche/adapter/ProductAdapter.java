package soexample.umeng.com.gouwuche.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.gouwuche.R;
import soexample.umeng.com.gouwuche.bean.Product;
import soexample.umeng.com.gouwuche.utils.StringUtils;
import soexample.umeng.com.gouwuche.weight.AddDecreaseView;

/**
 * Created by Shinelon on 2018/10/23.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> list;
    public interface OnProductClickListener {
        void onProductClick(int position, boolean isChecked);
    }

    private OnProductClickListener productClickListener;

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.productClickListener = listener;
    }

    public interface OnAddDecreaseProductListener {
        void onChange(int position, int num);
    }

    private OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }


    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_product, null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Product product = list.get(position);
        String images = product.getImages();
        if (!TextUtils.isEmpty(images)){
            String[] split = images.split("\\|");
            if (split.length>0){
                Glide.with(context).load(StringUtils.getRescpl(split[0])).into(holder.imgProduct);
            }
        }
        holder.txtProductName.setText(product.getTitle());
        holder.txtSinglePriice.setText(String.valueOf(product.getPrice()));
        holder.advProduct.setNUm(product.getNum());
        holder.advProduct.setOnAddDecreaseClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {
                product.setNum(num);
                if (productListener!=null){
                    productListener.onChange(position,num);
                }
            }

            @Override
            public void decrease(int num) {
                product.setNum(num);
                if (productListener!=null){
                    productListener.onChange(position,num);
                }
            }
        });

        holder.cbProduct.setOnCheckedChangeListener(null);
        holder.cbProduct.setChecked(product.isChecked());
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                product.setChecked(b);
                if (productClickListener!=null){
                   productClickListener.onProductClick(position,b);
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbProduct;
        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtSinglePriice;
        private AddDecreaseView advProduct;
        public ViewHolder(View itemView) {
            super(itemView);
            cbProduct = itemView.findViewById(R.id.cb_product);
            imgProduct = itemView.findViewById(R.id.img_product);
            txtSinglePriice = itemView.findViewById(R.id.txt_single_price);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
            advProduct = itemView.findViewById(R.id.adv_product);
        }
    }
}
