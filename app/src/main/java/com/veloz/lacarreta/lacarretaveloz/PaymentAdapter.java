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
import android.widget.Toast;

import java.util.ArrayList;

public class PaymentAdapter extends ArrayAdapter implements View.OnClickListener{

    private ArrayList<PaymentMethodModel> dataSet;
    Context mcontext;

    private int lastPosition = -1;

    private static class ViewHolder{
        ImageView img;
        TextView displaytxt;
    }

    public PaymentAdapter(Context context,@NonNull ArrayList<PaymentMethodModel> data) {
        super(context,R.layout.list_with_icon_item, data);
        this.dataSet = data;
        this.mcontext = context;
    }

    @Override
    public void onClick(View v) {

        int position = (int)v.getTag();
        Object obj = getItem(position);
        Services_model dmod = (Services_model)obj;


        AlertDialog.Builder al = new AlertDialog.Builder(mcontext);
        al.setTitle("DETALLES");
        al.setMessage("Fecha: " + dmod.getFecha()+"\n"+"Distancia: "+dmod.getDistancia()+"\n"+"Costo: "+dmod.getCosto()+"\n"+"Conductor: "+dmod.getConductor());
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

        Services_model md = (Services_model) getItem(position);

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

        vholder.displaytxt.setText(md.getFecha());
        vholder.img.setImageResource(R.drawable.ic_logistics_delivery_truck);

        vholder.displaytxt.setOnClickListener(this);
        vholder.displaytxt.setTag(position);

        return convertView;

    }
}
