package soexample.umeng.com.gouwuche.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import soexample.umeng.com.gouwuche.R;

/**
 * Created by Shinelon on 2018/10/24.
 */

public class AddDecreaseView extends RelativeLayout implements View.OnClickListener {
    private TextView txtAdd;
    private TextView txtDecrease;
    private TextView txtNum;

    private int num;



    public interface OnAddDecreaseClickListener {
        void add(int num);

        void decrease(int num);
    }
    private OnAddDecreaseClickListener listener;
    public void setOnAddDecreaseClickListener(OnAddDecreaseClickListener listener) {
        this.listener = listener;
    }

    public AddDecreaseView(Context context) {
        this(context, null);
    }

    public AddDecreaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddDecreaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.item_add_decrease,this);
        txtAdd = findViewById(R.id.txt_add);
        txtDecrease = findViewById(R.id.txt_decrease);
        txtNum = findViewById(R.id.txt_num);

        txtNum.setText("1");
        txtAdd.setOnClickListener(this);
        txtDecrease.setOnClickListener(this);
    }

    public void setNUm(int num){
        this.num=num;
        txtNum.setText(num+"");
    }
    public int getNum(){
        return num;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_add:
                num++;
                txtNum.setText(num + "");
                if (listener != null) {
                    listener.add(num);
                }
                break;
            case R.id.txt_decrease:
                if (num > 1) {
                    num--;
                }
                txtNum.setText(num + "");
                if (listener != null) {
                    listener.decrease(num);
                }
                break;
        }
    }

}
