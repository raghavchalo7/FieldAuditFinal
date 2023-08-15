package com.chalo.fieldauditapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionBinding
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding
import com.google.zxing.integration.android.IntentIntegrator

class BusSelectionFragment : Fragment() {

    lateinit var toggle: ActionBarDrawerToggle
    private var _binding: FragmentBusSelectionBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentBusSelectionBinding.inflate(inflater,container, false)

        binding.redirectBusSelectToBusSelectDisp.setOnClickListener {
//            IntentIntegrator().initiateScan()
            IntentIntegrator.forSupportFragment(this).initiateScan();
        }

        toggle = ActionBarDrawerToggle(activity,binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding!!.navView.setNavigationItemSelectedListener {
            Log.d("TAG1","5")
            when(it.itemId) {
                R.id.audit -> Toast.makeText(context,"Audit Button Pressed.............",
                    Toast.LENGTH_SHORT).show()
            }
            true
        }

//        binding.redirectBusSelectToBusSelectDisp.setOnClickListener {
//            findNavController().navigate(R.id.action_busSelectionFragment_to_busSelectionDispFragment)
//        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                val dataResult=result.contents
                val action=BusSelectionFragmentDirections.actionBusSelectionFragmentToBusSelectionDispFragment(dataResult)
                findNavController().navigate(action)

            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}