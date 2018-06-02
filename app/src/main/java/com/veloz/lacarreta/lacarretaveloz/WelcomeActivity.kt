package com.veloz.lacarreta.lacarretaveloz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val sginbtn = findViewById<Button>(R.id.btn_signin)
        val sgupbtn = findViewById<Button>(R.id.btn_signup)

        sginbtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext,SignInActivity::class.java)
            startActivity(intent)
        })

        sgupbtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext,SignUpActivity::class.java)
            startActivity(intent)
        })

    }
}
