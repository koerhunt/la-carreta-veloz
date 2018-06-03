package com.veloz.lacarreta.lacarretaveloz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class AccountActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    TextView numcon;
    TextView nom;
    TextView car;
    TextView sem;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ListView lista=null;
        ArrayAdapter<String> adapter;
        ArrayList<String> arraylist;


        numcon = (TextView)findViewById(R.id.nc);
        nom = (TextView)findViewById(R.id.n);
        car = (TextView)findViewById(R.id.c);
        sem = (TextView)findViewById(R.id.s);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //URL DE LA BASE DE DATOS

        mDatabase = FirebaseDatabase.getInstance().getReference(("usuarios/"+currentUser.getUid().toString()));


        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot sn;
                int count=0;
                Iterator<DataSnapshot> iterador = dataSnapshot.getChildren().iterator();

                while(iterador.hasNext()){
                    switch (count){
                        case 0: //carrera
                                car.setText(iterador.next().getValue().toString());
                            break;
                        case 1: //nocontrol
                                numcon.setText(iterador.next().getValue().toString());
                            break;
                        case 2: //nombre
                                numcon.setText(iterador.next().getValue().toString());
                            break;
                        case 3: //semestre
                                sem.setText(iterador.next().getValue().toString());
                            break;
                    }
                    count++;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
