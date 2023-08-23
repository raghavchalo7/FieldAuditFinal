package com.chalo.fieldauditapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.chalo.fieldauditapp.databinding.ActivitySplashScreenBinding
import com.chalo.fieldauditapp.databinding.FragmentBusDetailsDoneBinding

class SplashScreenActivity : AppCompatActivity() {

    private var _binding: ActivitySplashScreenBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        binding.ivChalo.alpha=0f
        binding.ivChalo.animate().setDuration(1000).alpha(1f).withEndAction {
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }


        val view = binding.root
        setContentView(view)
    }
}