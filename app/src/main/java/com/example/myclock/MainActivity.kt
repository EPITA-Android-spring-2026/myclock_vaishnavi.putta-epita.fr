package com.example.myclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        loadFragment(ClockFragment())

        bottomNav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.nav_clock -> loadFragment(ClockFragment())

                R.id.nav_alarm -> loadFragment(AlarmFragment())

                R.id.nav_timer -> loadFragment(TimerFragment())

                R.id.nav_stopwatch -> loadFragment(StopwatchFragment())
            }

            true
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}