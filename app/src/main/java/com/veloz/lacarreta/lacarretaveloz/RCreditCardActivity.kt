package com.veloz.lacarreta.lacarretaveloz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RCreditCardActivity : AppCompatActivity() {

    //Firebase DATABASE
    var database: FirebaseDatabase? = null
    var mrfdb: DatabaseReference? = null

    //FIREBASE AUTH
    var mAuth: FirebaseAuth? = null


    //resources
    var tarjeta : EditText? = null
    var exp: EditText? = null
    var cvs: EditText? = null
    var titular: EditText? = null

    var btn: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rcredit_card)

        //FIREBASE ATUH
        mAuth = FirebaseAuth.getInstance()

        //FIREBASE DATABASE
        database = FirebaseDatabase.getInstance()

        // Check if user is signed in (non-null) and update UI accordingly.
        var currentUser = mAuth?.currentUser


        //campos
        tarjeta = findViewById(R.id.r_notarjeta)
        exp = findViewById(R.id.r_exp)
        cvs = findViewById(R.id.r_svc)
        titular = findViewById(R.id.r_titular)

        //btn
        btn = findViewById(R.id.r_send_tarjeta)

        //BUTTON ACTION
        btn!!.setOnClickListener { v ->

            //FIREBASE DB
            mrfdb = database?.getReference(("pagos/"+currentUser?.uid!!))

            var pm = PaymentMethodModel(
                    tarjeta!!.text.toString(),
                    cvs!!.text.toString(),
                    exp!!.text.toString(),
                    titular!!.text.toString()
            )

            var key = mrfdb!!.push().key

            mrfdb = database?.getReference(("pagos/"+currentUser?.uid!!)+"/"+key)

            mrfdb!!.setValue(pm).addOnCompleteListener(OnCompleteListener { task ->
                Toast.makeText(this,"TARJETA REGISTRADA",Toast.LENGTH_LONG).show()
                finish()
            })


        }

    }
}
