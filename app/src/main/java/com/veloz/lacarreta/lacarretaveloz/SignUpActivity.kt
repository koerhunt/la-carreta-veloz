package com.veloz.lacarreta.lacarretaveloz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log


class SignUpActivity : AppCompatActivity() {

    private var gotosignup : Button? = null

    var email : EditText? = null
    var passwd : EditText? = null

    private var nocontrol : EditText? = null
    private var telefono : EditText? = null
    private var carrera : EditText? = null
    private var semestre : EditText? = null



    //FIREBASE AUTH
    var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //LAYOUT BINDING
        gotosignup = findViewById<Button>(R.id.gotosignup)
        nocontrol = findViewById<EditText>(R.id.nocontrol)
        email = findViewById<EditText>(R.id.email )
        passwd = findViewById<EditText>(R.id.passwd)
        telefono = findViewById<EditText>(R.id.telefono)
        carrera = findViewById<EditText>(R.id.carrera)
        semestre = findViewById<EditText>(R.id.semestre)

        gotosignup= findViewById<Button>(R.id.gotosignup)

        //FIREBASE ATUH
        mAuth = FirebaseAuth.getInstance()
        
        // Check if user is signed in (non-null) and update UI accordingly.
        var currentUser = mAuth?.currentUser

        //BUTTON ACTION
        gotosignup?.setOnClickListener { v ->

            if(!(email?.text.toString().equals("")||passwd?.text.toString().equals("")||email==null||passwd==null)){

                mAuth!!.createUserWithEmailAndPassword(email?.text.toString(),passwd?.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {

                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(applicationContext,"USUARIO REGISTRADO",Toast.LENGTH_SHORT).show()
                                currentUser = mAuth!!.getCurrentUser()

                                val intent = Intent(applicationContext,HomeActivity::class.java)
                                startActivity(intent)
                                finish()

                            } else {

                                // If sign in fails, display a message to the user.
                                Toast.makeText(applicationContext,"USUARIO NO REGISTRADO",Toast.LENGTH_SHORT).show()

                            }

                        }
                    }
                }

            }
        }
