package com.chalo.fieldauditapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.chalo.fieldauditapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var toggle: ActionBarDrawerToggle

    private var _binding: ActivityMainBinding?=null
    private val binding get() = _binding!!

    lateinit var nview: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //setDrawerEnabled(true)


        nview=binding.navView
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
       binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
//
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



//        _binding!!.navView.setNavigationItemSelectedListener {
//            Log.d("TAG1","5")
//            when(it.itemId) {
////                R.id.audit -> Toast.makeText(context,"Audit Button Pressed.............",
////                    Toast.LENGTH_SHORT).show()
//
////                R.id.nav_host_fragment
//                R.id.audit -> findNavController(R.id.nav_host_fragment).navigate(R.id.busSelectionDispFragment)
//                R.id.logout -> {
//
//                    logout()
////                                    val sharedPreferences = activity?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
////                                    val editor= sharedPreferences?.edit()
////                                    editor?.remove("token")?.apply()
////
////                                    activity?.let{
////                                        val intent = Intent (it, SplashScreenActivity::class.java)
////                                        it.startActivity(intent)
////                                        (activity as AppCompatActivity).finish()
////                                    }
//
//                }
//            }
//            true
//        }


        var IS_LOGGED_IN: Boolean
        val sharedPreferences = this.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val token = sharedPreferences?.getString("token", null)
        //Toast.makeText(this, "toast=${token}", Toast.LENGTH_LONG).show()
        IS_LOGGED_IN = token != null
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        //val nvc=findNavController(R.id.nav_host_fragment)




        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController = navHostFragment.navController


//        //Fragment fragment = new FragmentOne()
//        FragmentTransaction fragmentTransaction=getSupportManager().beginTransaction();
//        val fr:FragmentTransaction=getSupportManager().beginTransaction();
//        fragmentTransaction.replace(R.id.container,fragment).commit();

        //val IS_PRIVACY_POLICY_ACCEPTED:String="true"//for now

        //Log.d("Token======",token.toString())
        val destination =
            if (IS_LOGGED_IN == false) R.id.loginFragment else R.id.busSelectionFragment
        if(IS_LOGGED_IN==true)
        {
            val tok= token?.subSequence(1, (token?.length )?.minus(1) ?: 0)
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.userIdTV).text=tok
        }
        else
        {
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.userIdTV).text="UserId"
        }
        navGraph.setStartDestination(destination)
        navController.graph = navGraph





        val toolbar: androidx.appcompat.widget.Toolbar? =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration.Builder(R.id.loginFragment,R.id.busSelectionFragment).build()

        setupActionBarWithNavController(navController,appBarConfiguration)

        _binding!!.navView.setNavigationItemSelectedListener {
            if (toolbar != null) {
                toolbar.setNavigationOnClickListener { onBackPressed() }
            }
            Log.d("TAG1","5")
            when(it.itemId) {
//                R.id.audit -> Toast.makeText(context,"Audit Button Pressed.............",
//                    Toast.LENGTH_SHORT).show()

//                R.id.nav_host_fragment
                R.id.audit -> //navController.navigate(R.id.action_busSelectionFragment_to_auditReportFragment)
                {
//                    navController.navigate(R.id.auditReport2Fragment)
                    navController.navigate(R.id.action_busSelectionFragment_to_auditReport2Fragment)


//                    return@setNavigationItemSelectedListener NavigationUI.onNavDestinationSelected(R.id.audit, navController)
//                    return NavigationUI.onNavDestinationSelected(item, navController);
//                    //setContentView(R.layout.fragment_audit_report)
//                    val mFragmentManager = supportFragmentManager
//                    val mFragmentTransaction = mFragmentManager.beginTransaction()
//                    val mFragment = AuditReport2Fragment()
//                    mFragmentTransaction.replace(R.id.nav_host_fragment, mFragment)
//                    mFragmentTransaction.addToBackStack(null)
//                    mFragmentTransaction.commit()
//                    binding.drawerLayout.closeDrawers()
                }
                R.id.logout -> {

                    logout()
//                                    val sharedPreferences = activity?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
//                                    val editor= sharedPreferences?.edit()
//                                    editor?.remove("token")?.apply()
//
//                                    activity?.let{
//                                        val intent = Intent (it, SplashScreenActivity::class.java)
//                                        it.startActivity(intent)
//                                        (activity as AppCompatActivity).finish()
//                                    }

                }
            }
            true
        }





//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//
//        val navController = navHostFragment.navController
//        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
//        navGraph.setStartDestination(R.id.busSelectionFragment)
//        navController.graph = navGraph


        //val dr:DrawerLayout=findViewById(BusSelectionFragment.d)
//        val appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawerLayout)


        //appBarConfiguration = AppBarConfiguration(navController.graph, R.id.drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        val toolbar: androidx.appcompat.widget.Toolbar? =
//            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)










    }

    override fun onResume() {
        super.onResume()
//        var IS_LOGGED_IN: Boolean
//        val sharedPreferences = this.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
//        val token = sharedPreferences?.getString("token", null)
//        //Toast.makeText(this, "toast=${token}", Toast.LENGTH_LONG).show()
//        IS_LOGGED_IN = token != null
//        if(IS_LOGGED_IN==true)
//        {
//            val tok= token?.subSequence(1, (token?.length )?.minus(1) ?: 0)
//            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.userIdTV).text=tok
//        }
//        else
//        {
//            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.userIdTV).text="UserId"
//        }
    }

    fun userName(){
        var IS_LOGGED_IN: Boolean
        val sharedPreferences = this.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val token = sharedPreferences?.getString("token", null)
        //Toast.makeText(this, "toast=${token}", Toast.LENGTH_LONG).show()
        IS_LOGGED_IN = token != null
        if(IS_LOGGED_IN==true)
        {
            val tok= token?.subSequence(1, (token?.length )?.minus(1) ?: 0)
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.userIdTV).text=tok
        }
        else
        {
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.userIdTV).text="UserId"
        }
    }

    override fun onBackPressed() {

//        val fragmentOne:BusSelectionFragment = supportFragmentManager.findFragmentByTag("BusSelectionFragment");
//        if (fragmentOne != null && fragmentOne.isVisible())

//        val fragmentInstance = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
////        val ffe:BusSelectionDispFragment
////        if(navController.currentDestination==navController.graph.findNode(R.id.busSelectionDispFragment))
//        //if(BusSelectionDispFragmen)
//        if(BusSelectionDispFragment.)
//        if(navController.currentDestination==navController.findDestination(R.id.busSelectionFineFragment))
//        {
//            Toast.makeText(this,"NOBACKPRESS...",Toast.LENGTH_LONG).show()
//        }
        if(navController.currentDestination==navController.findDestination(R.id.loginFragment) || navController.currentDestination==navController.findDestination(R.id.busSelectionFragment))
        {
                finishAffinity()
            finish()
        }
//        else if(navController.currentDestination==navController.findDestination(R.id.busSelectionFineFragment))
//        {
//            Toast.makeText(this,"NOBACKPRESS...",Toast.LENGTH_LONG).show()
//        }
        else {
        super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment)
        return (navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp())
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item))
//        {
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

//    override fun onBackPressed() {
//        finishAffinity()
//    }

    fun logout() {
            val sharedPreferences = this?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.remove("token")?.apply()

            this?.let {
                val intent = Intent(it, SplashScreenActivity::class.java)
                it.startActivity(intent)
                (this as AppCompatActivity).finishAffinity()
                (this as AppCompatActivity).finish()
            }
    }

    fun setDrawerEnabled(enabled: Boolean) {
        val lockMode =
            if (enabled) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        binding.drawerLayout.setDrawerLockMode(lockMode)
        toggle.isDrawerIndicatorEnabled = enabled
    }

    fun makeVis(){
        val showAuditReport:TextView=findViewById(R.id.showAuditReport)
        showAuditReport.visibility=View.VISIBLE
    }

    fun makeGone(){
        val showAuditReport:TextView=findViewById(R.id.showAuditReport)
        showAuditReport.visibility=View.GONE
    }

}