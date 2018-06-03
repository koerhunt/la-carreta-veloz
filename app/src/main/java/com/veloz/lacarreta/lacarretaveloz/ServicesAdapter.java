package com.veloz.lacarreta.lacarretaveloz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ServicesAdapter extends ArrayAdapter implements View.OnClickListener{

    private ArrayList<PaymentMethodModel> dataSet;
    Context mcontext;

    private static class ViewHolder{
        ImageView img;
        TextView displaytxt;
    }

    public ServicesAdapter(@NonNull ArrayList<PaymentMethodModel> data, Context context) {
        super(context,R.layout.services_item, data);
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
