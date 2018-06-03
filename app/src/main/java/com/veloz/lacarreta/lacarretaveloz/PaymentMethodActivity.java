package com.veloz.lacarreta.lacarretaveloz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class PaymentMethodActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ListView lista=null;
    ArrayAdapter<String> adapter;
    ArrayList <PaymentMethodModel> arraylist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        //LISTVIEW
        lista = (ListView)findViewById(R.id.milista);

        //ARRAYLIST
        arraylist = new ArrayList<>();

        //ADAPTER
        adapter = new PaymentAdapter(this, arraylist);
        lista.setAdapter(adapter);

        //OBTENER AL USUARIO LOGEADO
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //URL DE LA BASE DE DATOS
        mDatabase = FirebaseDatabase.getInstance().getReference(("pagos/"+currentUser.getUid().toString()));


        //OBTENER DATOS DE LA BASE DE DATOS
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arraylist.removeAll(arraylist);

                Iterator<DataSnapshot> iterador = dataSnapshot.getChildren().iterator();

                while(iterador.hasNext()){

                    DataSnapshot a = iterador.next();

                    PaymentMethodModel pm = new PaymentMethodModel(
                            a.child("tarjeta").getValue().toString(),
                            a.child("cvs").getValue().toString(),
                            a.child("exp").getValue().toString(),
                            a.child("titular").getValue().toString()
                    );

                    arraylist.add(pm);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

