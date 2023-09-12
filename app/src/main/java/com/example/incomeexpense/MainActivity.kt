package com.example.incomeexpense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.incomeexpense.Fragment.AmountFragment
import com.example.incomeexpense.Fragment.ProfileFragment
import com.example.incomeexpense.Fragment.ReportFragment
import com.example.incomeexpense.Fragment.TransectionFragment
import com.example.incomeexpense.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener {
            when(it){
                0 -> {
//                    Toast.makeText(this, "0000", Toast.LENGTH_SHORT).show()
                    loadFragment(ProfileFragment())
                }
                1 -> {
                    loadFragment(AmountFragment())
                }
                2 -> {
                    loadFragment(TransectionFragment())
                }
                3 -> {
                    loadFragment(ReportFragment())
                }
                else->{
//                    Toast.makeText(this, "Else", Toast.LENGTH_SHORT).show()
                    loadFragment(ProfileFragment())
                }

            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragFrame,fragment)
            .commit()
    }

}