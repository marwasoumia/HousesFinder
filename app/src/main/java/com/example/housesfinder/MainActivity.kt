package com.example.housesfinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    //la liste partagé des annoces :
    companion object {
        var listAnnoce = ArrayList<Anonce>()
        var listSellers = ArrayList<Seller>()
       lateinit  var  mainSeller:Seller
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = FragmentHome()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_add -> {

                startActivity(Intent(this, AddDetailsActivity::class.java))

            }
            R.id.navigation_profile -> {
                val fragment = FragmentProfile()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    fun initListAnnonces(){
        listAnnoce.add(Anonce(6000.6,"Alger",1000000.05,"Villa", arrayListOf(
            Uri.parse(R.drawable.home1.toString()),Uri.parse(R.drawable.home2.toString()),Uri.parse(R.drawable.home3.toString())) ,
            listSellers[0]
        ))
        listAnnoce.add(Anonce(6000.6,"Jijel",1000000.05,"Villa", arrayListOf(
            Uri.parse(R.drawable.home2.toString())) ,
            listSellers[1]))

        listAnnoce.add(Anonce(6000.6,"Oran",1000000.05,"Villa", arrayListOf(
            Uri.parse(R.drawable.home3.toString())) ,
            listSellers[2]))

        listAnnoce.add(Anonce(6000.6,"Alger",1000000.05,"Appartement", arrayListOf(
            Uri.parse(R.drawable.home4.toString()) ),
            listSellers[3]))
    }

    fun initListSellers(){
        listSellers.add(mainSeller)
        listSellers.add(Seller("oussama","Bouhenniche","055189178","fo@ufc.dz"))
        listSellers.add(Seller("asma","Bouhenniche","055189178","fa@ufc.dz"))
        listSellers.add(Seller("maroua","Bourouais","055189178","fm@esi.dz"))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(listAnnoce.isEmpty()){
            initListSellers()
            initListAnnonces()
        }
        content = findViewById(R.id.frame_container)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = FragmentHome()
        addFragment(fragment)
    }







    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.frame_container, fragment, fragment.javaClass.getSimpleName())
            .addToBackStack(fragment.javaClass.getSimpleName())
            .commit()
    }




}
