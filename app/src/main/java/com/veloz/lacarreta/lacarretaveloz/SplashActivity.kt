package com.veloz.lacarreta.lacarretaveloz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null

    //Delay
    private val SPLASH_DELAY: Long = 3000

    //FIREBASE AUTH
    var mAuth: FirebaseAuth? = null


    internal val mRunnableToHome: Runnable = Runnable {
        if (!isFinishing) {


            mAuth = FirebaseAuth.getInstance()

            var currentUser = mAuth?.currentUser

            if(currentUser == null){
                var intent  = Intent(applicationContext,WelcomeActivity::class.java)
                startActivity(intent)
            }else{
                var intent  = Intent(applicationContext,HomeActivity::class.java)
                startActivity(intent)
            }

            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnableToHome, SPLASH_DELAY)
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnableToHome!!)
        }

        super.onDestroy()
    }


}
