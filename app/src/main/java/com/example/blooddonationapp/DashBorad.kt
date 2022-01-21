package com.example.blooddonationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.blooddonationapp.nav_pages.Add_Request_Page
import com.example.blooddonationapp.nav_pages.AllRequestPage
import com.example.blooddonationapp.nav_pages.HomePage
import com.example.blooddonationapp.nav_pages.Profile_Page
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashBorad : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_borad)
        //init
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
        toolbar = supportActionBar!!
        //listner
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //defalut page open
        toolbar.title = "Home Page"
        val homeFragment = HomePage.newInstance()
        openFragment(homeFragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {

                toolbar.title = "Home Page"
                val homeFragment = HomePage.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_request -> {
                toolbar.title = "All Request"
                val rquestFragment = AllRequestPage.newInstance()
                openFragment(rquestFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_add_request -> {
                toolbar.title = "Add Request"
                val addrquestFragment = Add_Request_Page.newInstance()
                openFragment(addrquestFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                toolbar.title = "Profile"
                val profileFragment = Profile_Page.newInstance()
                openFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content1, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}