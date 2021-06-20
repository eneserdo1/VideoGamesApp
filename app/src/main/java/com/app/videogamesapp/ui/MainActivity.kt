package com.app.videogamesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.videogamesapp.R
import com.app.videogamesapp.databinding.ActivityMainBinding
import com.app.videogamesapp.ui.FavouriteFragment.FavouriteFragment
import com.app.videogamesapp.ui.HomeFragment.HomeFragment
import com.app.videogamesapp.utils.MyViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()


    }

    private fun initViewPager() {
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "")
        adapter.addFragment(FavouriteFragment(), "")
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 0
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_favorite_24)
    }
}