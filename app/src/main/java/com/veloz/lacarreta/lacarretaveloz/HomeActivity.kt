package com.veloz.lacarreta.lacarretaveloz

import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    //FIREBASE AUTH
    var mAuth: FirebaseAuth? = null
    var current_user = mAuth?.currentUser

    var mDatabase: DatabaseReference? = null

    var t1 : TextView? = null
    var t2 : TextView? = null

    //mapa
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        //obtener al usuario logeado
        mAuth = FirebaseAuth.getInstance()
        current_user = mAuth?.currentUser

        //accion del boton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Obteniendo choferes, por favor espere...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        //menu lateral
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        drawer_layout
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //inicializacion del mapa
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_payments-> {
                var intent  = Intent(applicationContext,PaymentMethodActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_services -> {
                var intent  = Intent(applicationContext,ServicesActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_cuenta -> {
                var intent  = Intent(applicationContext,AccountActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_signout -> {
                mAuth?.signOut()
                var intent  = Intent(applicationContext,WelcomeActivity::class.java)
                startActivity(intent)
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!


        // Add a marker in Sydney and move the camera
        val itd = LatLng(24.032102,-104.646241)

        mMap.addMarker(MarkerOptions().position(itd).title("ITD"))
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(itd,14f) )


    }

}
