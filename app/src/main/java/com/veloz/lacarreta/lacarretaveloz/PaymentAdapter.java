package com.veloz.lacarreta.lacarretaveloz;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.images.ImageRequest;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends ArrayAdapter implements View.OnClickListener{

    private ArrayList<PaymentMethodModel> dataSet;
    Context mcontext;

    private int lastPosition = -1;

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

                final AlertDialog.Builder al = new AlertDialog.Builder(mcontext);
                al.setTitle("DETALLES");
                al.setMessage("aqui va lo que quieres imprimir");
                al.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                al.create();
                al.show();


                break;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PaymentMethodModel md = (PaymentMethodModel) getItem(position);

        ViewHolder vholder;

        final View result;

        if(convertView==null){

            vholder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.payment_method_item,parent,false);

            vholder.displaytxt = (TextView)convertView.findViewById(R.id.display_name_tag);

            result = convertView;

            convertView.setTag(vholder);

        }else{
            vholder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        vholder.displaytxt.setText(md.getNotarjeta());

        return convertView;

    }
}
