package com.veloz.lacarreta.lacarretaveloz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ServicesActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ListView lista = null;
    ArrayAdapter<String> adapter;
    ArrayList<String> arraylist;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        lista = (ListView) findViewById(R.id.listaserv);
        arraylist = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraylist);
        lista.setAdapter(adapter);

        //OBTENER AL USUARIO LOGEADO
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //URL DE LA BASE DE DATOS
        mDatabase = FirebaseDatabase.getInstance().getReference(("servicios/" + currentUser.getUid().toString()));


        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot sn;
                arraylist.removeAll(arraylist);

                Iterator<DataSnapshot> iterador = dataSnapshot.getChildren().iterator();

                while (iterador.hasNext()) {

                    arraylist.add(iterador.next().getValue().toString());
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}


        /*final AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("DETALLES");
        al.setMessage("aqui va lo que quieres imprimir");
        al.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        al.create();
        al.show();*/

