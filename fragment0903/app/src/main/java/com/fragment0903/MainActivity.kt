package com.fragment0903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fragment0903.ItemFragment
import com.fragment0903.ImageFragment
import com.fragment0903.R
import com.fragment0903.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val textarr = arrayListOf<String>("이미지","리스트")
    val iconarr = arrayListOf<Int>(R.drawable.ic_baseline_10k_24,R.drawable.ic_baseline_adb_24)
    val imgFragment = ImageFragment()
    val itemFragment = ItemFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initlayout()
    }

    private fun initlayout() {
       binding.viewpager.adapter = MyViewPageradpapter(this)
        TabLayoutMediator(binding.tabLayout,binding.viewpager){
        tab,position->
            tab.text = textarr[position]
            tab.setIcon(iconarr[position])
        }.attach()

    }
}