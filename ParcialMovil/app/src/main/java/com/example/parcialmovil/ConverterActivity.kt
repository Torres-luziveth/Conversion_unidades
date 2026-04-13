package com.example.parcialmovil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val adaptador = ConverterPagerAdapter(this)
        viewPager.adapter = adaptador

        TabLayoutMediator(tabLayout, viewPager) { tab, posicion ->
            when (posicion) {
                0 -> tab.text = "Longitud"
                1 -> tab.text = "Masa"
                2 -> tab.text = "Temperatura"
            }
        }.attach()
    }
}