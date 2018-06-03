package com.veloz.lacarreta.lacarretaveloz;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ServicesAdapter extends ArrayAdapter implements View.OnClickListener{

    private ArrayList<Services_model> dataSet;
    Context mcontext;

    private int lastPosition = -1;

    private static class ViewHolder{
        ImageView img;
        TextView displaytxt;
    }

    public ServicesAdapter(Context context, @NonNull ArrayList<Services_model> data) {
        super(context,R.layout.list_with_icon_item, data);
        this.dataSet = data;
        this.mcontext = context;
    }

    @Override
    public void onClick(View v) {

        int position = (int)v.getTag();
        Object obj = getItem(position);
        PaymentMethodModel dmod = (PaymentMethodModel)obj;


        AlertDialog.Builder al = new AlertDialog.Builder(mcontext);
        al.setTitle("DETALLES");
        al.setMessage(dmod.getNotarjeta()+"\n"+"Titular: "+dmod.titular);
        al.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        al.create();
        al.show();


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

            convertView = inflater.inflate(R.layout.list_with_icon_item,parent,false);

            vholder.displaytxt = (TextView)convertView.findViewById(R.id.display_name_tag);
            vholder.img = (ImageView)convertView.findViewById(R.id.list_icon);

            result = convertView;

            convertView.setTag(vholder);

        }else{
            vholder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        vholder.displaytxt.setText(md.getNotarjeta());
        vholder.img.setImageResource(R.drawable.ic_credit_card_2);

        vholder.displaytxt.setOnClickListener(this);
        vholder.displaytxt.setTag(position);

        return convertView;

    }
}
