package com.veloz.lacarreta.lacarretaveloz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.maps.android.PolyUtil


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    //FIREBASE AUTH
    var mAuth: FirebaseAuth? = null
    var current_user = mAuth?.currentUser

    var database: FirebaseDatabase? = null
    var mrfdb: DatabaseReference? = null

    var mrfcdb: DatabaseReference? = null

    var t1 : TextView? = null
    var t2 : TextView? = null

    //mapa
    private lateinit var mMap: GoogleMap


    var nview  : NavigationView? = null
    var hview : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        //obtener al usuario logeado
        mAuth = FirebaseAuth.getInstance()
        current_user = mAuth?.currentUser

        //FIREBASE DB
        database = FirebaseDatabase.getInstance()


        //accion del boton
        fab.setOnClickListener { view ->

            Snackbar.make(view, "Obteniendo choferes, por favor espere...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            mrfcdb = database!!.getReference()
            var myTopPostsQuery = mrfcdb!!.child("servicios")


            var listener2 = HomeConductoresRetriveData()
            listener2.view = view
            listener2.mcontext = this
            listener2.mMap= mMap

            myTopPostsQuery!!.addValueEventListener(listener2)


        }


        //menu lateral
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)


        //get the edit text for show the user info
        nview  = findViewById(R.id.nav_view)
        hview = nview!!.getHeaderView(0)
        t1 = hview?.findViewById(R.id.username)
        t2 = hview?.findViewById(R.id.userphone)


        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)


        //inicializacion del mapa
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mrfdb = database?.getReference(("usuarios/"+current_user?.uid!!))

        var listener : HomeValueEventListener = HomeValueEventListener()

        listener.t1 = this.t1
        listener.t2 = this.t2

        mrfdb!!.addValueEventListener(listener)


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
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(itd,14f))

    }

    class HomeValueEventListener : ValueEventListener {

        var t1 : TextView? = null
        var t2 : TextView? = null

        override fun onDataChange(data: DataSnapshot) {
            t1?.text = data.child("nombre").getValue().toString()
            t2?.text = data.child("telefono").getValue().toString()
        }

        override fun onCancelled(p0: DatabaseError) {
        }

    }


    class HomeConductoresRetriveData : ValueEventListener {

        var view : View? = null
        var mcontext : Context? = null
        var mMap : GoogleMap? = null


        override fun onCancelled(p0: DatabaseError) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            var c = 0

            var lastdata : DataSnapshot? = null

            for (postSnapshot in dataSnapshot.children) {

                for(post2Snapshot in postSnapshot.children){

                    if(post2Snapshot.child("estado").value ==true){
                        lastdata = post2Snapshot
                        c++
                    }

                }
            }

            Snackbar.make(view!!, "Encontrados: "+c+" conductores disponibles", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            if(lastdata!=null){
                val al = AlertDialog.Builder(mcontext!!)
                al.setTitle("DETALLES")
                al.setMessage(lastdata.child("conductor").getValue().toString())
                al.setIcon(R.drawable.ic_logistics_delivery_truck)
                al.setPositiveButton("Confirmar") { dialogInterface, i ->
                    var s = "mmqqCfvq}RwGPyDFLfEX`KTtJLdFFfD{@?s@@gAFgGJ_DH_FFgJRuDDuDN}KN[@@nCoBN_Jx@gGf@U@f@fHd@bHl@~IvAfSFz@MXNPfAtAFjAXbELx@eFbAuDv@SuA"

                    var decodedPoints : List<LatLng>  = CarretaUtils.decode(s)
                    var options  = PolylineOptions()

                    options.width(6f)
                    options.color(Color.RED)
                    options.addAll(decodedPoints)

                    mMap!!.addPolyline(options)
                }
                al.setNegativeButton("Cancelar") { dialogInterface, i -> dialogInterface.cancel() }
                al.create()
                al.show()

            }

        }





    }




}
