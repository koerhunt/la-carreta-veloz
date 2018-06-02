package com.veloz.lacarreta.lacarretaveloz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentMethodActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ListView lista=null;
    ArrayAdapter<String> adapter;
    ArrayList <String> arraylist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        lista = (ListView)findViewById(R.id.milista);
        arraylist = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraylist);
        lista.setAdapter(adapter);


        mDatabase = FirebaseDatabase.getInstance().getReference("pagos");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String v1 = dataSnapshot.getValue(String.class);
                arraylist.add(v1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*setContentView(R.layout.activity_payment_method);
        ListView lista;
        ArrayAdapter<String> adaptador;
        lista = (ListView)findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);*/




    }
}
