package com.elviraminnullina.veo_ride_test_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = FragmentBoarding.getInstance(Boarding.boarding_one)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
           // .addToBackStack(null)
            .commit()
    }
}
