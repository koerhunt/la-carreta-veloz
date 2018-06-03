package com.veloz.lacarreta.lacarretaveloz;

import android.accounts.Account;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class AccountActivity extends AppCompatActivity {


    TextView numcon;
    TextView nom;
    TextView car;
    TextView sem;

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    Button delaccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        numcon = (TextView)findViewById(R.id.nc);
        nom = (TextView)findViewById(R.id.n);
        car = (TextView)findViewById(R.id.c);
        sem = (TextView)findViewById(R.id.s);

        delaccount = (Button)findViewById(R.id.delete_account_btn);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        //URL DE LA BASE DE DATOS
        mDatabase = FirebaseDatabase.getInstance().getReference(("usuarios/"+currentUser.getUid().toString()));


        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

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
                            nom.setText(iterador.next().getValue().toString());
                            break;
                        case 3: //semestre
                            sem.setText(iterador.next().getValue().toString());
                            break;
                        default:
                            iterador.next();

                    }
                    count++;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });


        delaccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder bulder = new AlertDialog.Builder(AccountActivity.this);
                bulder.setMessage("¿SEGURO QUE DESEA ELIMINAR SU CUENTA?");

                bulder.setTitle("Eliminar cuenta");

                bulder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AccountActivity.this,"CUENTA ELIMINADA", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(AccountActivity.this,WelcomeActivity.class);
                                startActivity(i);
                                finish();
                            }
                        });
                    }
                });

                bulder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                AlertDialog dgl = bulder.create();
                dgl.show();




            }


        });

    }
}
