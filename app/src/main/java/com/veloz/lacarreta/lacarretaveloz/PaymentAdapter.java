package com.veloz.lacarreta.lacarretaveloz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.images.ImageRequest;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends ArrayAdapter implements View.OnClickListener{

    private ArrayList<PaymentMethodModel> dataSet;
    Context mcontext;

    private static class ViewHolder{
        ImageView img;
        TextView displaytxt;
    }

    public PaymentAdapter(@NonNull ArrayList<PaymentMethodModel> data, Context context) {
        super(context,R.layout.payment_method_item, data);
        this.dataSet = data;
        this.mcontext = context;
    }

    @Override
    public void onClick(View v) {

        int position = (Integer)v.getTag();
        Object obj = getItem(position);

        switch (v.getId()){
            case R.id.display_name_tag:


                


                break;
        }



    }
}
