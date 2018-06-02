package com.veloz.lacarreta.lacarretaveloz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PaymentMethodActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        /*setContentView(R.layout.activity_payment_method);
        ListView lista;
        ArrayAdapter<String> adaptador;
        lista = (ListView)findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);*/


        final ListView lista = (ListView)findViewById(R.id.milista);
        String[] values = new String[]{"Ironman","Capitan America","Hulk","Thor","Black Widow","Ant man","Spider man"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        lista.setAdapter(adapter);

    }
}
