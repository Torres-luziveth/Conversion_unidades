package com.example.parcialmovil

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ConverterPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LengthFragment()
            1 -> MassFragment()
            2 -> TemperatureFragment()
            else -> LengthFragment()
        }
    }
}