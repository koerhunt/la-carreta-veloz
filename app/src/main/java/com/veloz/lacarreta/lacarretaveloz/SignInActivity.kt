package com.veloz.lacarreta.lacarretaveloz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    private var btngotosignin : Button? = null

    var txemailsin: EditText? = null
    var txpassword : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mAuth = FirebaseAuth.getInstance()

        btngotosignin = findViewById(R.id.btngotosignin)

        txemailsin = findViewById(R.id.txemailsin)
        txpassword = findViewById(R.id.txpassword)

        btngotosignin?.setOnClickListener({


            if(txemailsin!!.text.toString().equals("")||txpassword!!.text.toString().equals("")){
               Toast.makeText(this,"Por favor llena los campos",Toast.LENGTH_LONG).show()
            }else{
                mAuth!!.signInWithEmailAndPassword(txemailsin?.text.toString(),txpassword?.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(applicationContext,HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(applicationContext,"USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show()
                            }
                        }
            }

        })

    }
}
