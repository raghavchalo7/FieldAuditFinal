package com.chalo.fieldauditapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)


        var IS_LOGGED_IN: Boolean
        val sharedPreferences = this.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val token = sharedPreferences?.getString("token", null)
        Toast.makeText(this, "toast=${token}", Toast.LENGTH_LONG).show()
        IS_LOGGED_IN = token != null
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()


        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController = navHostFragment.navController

        //val IS_PRIVACY_POLICY_ACCEPTED:String="true"//for now

        val destination =
            if (IS_LOGGED_IN == false) R.id.loginFragment else R.id.busSelectionFragment
        navGraph.setStartDestination(destination)
        navController.graph = navGraph


//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//
//        val navController = navHostFragment.navController
//        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
//        navGraph.setStartDestination(R.id.busSelectionFragment)
//        navController.graph = navGraph


        //val dr:DrawerLayout=findViewById(BusSelectionFragment.d)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        //appBarConfiguration = AppBarConfiguration(navController.graph, R.id.drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
        val toolbar: androidx.appcompat.widget.Toolbar? =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun logout() {
            val sharedPreferences = this?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.remove("token")?.apply()

            this?.let {
                val intent = Intent(it, SplashScreenActivity::class.java)
                it.startActivity(intent)
                (this as AppCompatActivity).finish()
            }
    }
}