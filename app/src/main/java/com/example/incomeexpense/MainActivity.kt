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
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        var Type = arrayOf  ("profile","amount","transection","report")
//        var Fragments = arrayOf(ProfileFragment(),AmountFragment(),TransectionFragment(),ReportFragment())
//        loadFragment(ProfileFragment())
//        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNav)
//            .setOnNavigationItemSelectedListener {object : OnNavigationItemSelectedListener{
//                override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                    when(item.itemId) {
//                        R.id.profile->{
//                            loadFragment(ProfileFragment())
//                        }
//                    }
//                    when(item.itemId) {
//                        R.id.addAmount->{
//                            loadFragment(AmountFragment())
//                        }
//                    }
//                    when(item.itemId) {
//                        R.id.transection->{
//                            loadFragment(TransectionFragment())
//                        }
//                    }
//                    when(item.itemId) {
//                        R.id.report->{
//                            loadFragment(ReportFragment())
//                        }
//                    }
//                    return true
//                }
//            }

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.setOnNavigationItemSelectedListener{item->
            when(item.itemId){
                R.id.profile->{
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.addAmount->{
                    loadFragment(AmountFragment())
                    true
                }
                R.id.transection->{
                    loadFragment(TransectionFragment())
                    true
                }
                R.id.report->{
                    loadFragment(ReportFragment())
                    true
                }
                else->false

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
