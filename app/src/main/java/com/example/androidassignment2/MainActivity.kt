package com.example.androidassignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.example.androidassignment2.adapters.ViewPagerAdapter
import com.example.androidassignment2.fragments.ClassicFragment
import com.example.androidassignment2.fragments.PopFragment
import com.example.androidassignment2.fragments.RockFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        showTabs()
    }

    private fun showTabs() {
        // init the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragments to the list
        adapter.addFragment(RockFragment(), "Rock")
        adapter.addFragment(ClassicFragment(), "Classic")
        adapter.addFragment(PopFragment(), "Pop")

        // Adding the adapter to the ViewPager
        pager.adapter = adapter

        // bind the viewPager with the TabLayout
        tab.setupWithViewPager(pager)

        tab.getTabAt(0)!!.setIcon(R.drawable.rock_24)
        tab.getTabAt(1)!!.setIcon(R.drawable.classic_24)
        tab.getTabAt(2)!!.setIcon(R.drawable.pop_24)
    }

    private fun initViews() {
        pager = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tabs)
    }
}