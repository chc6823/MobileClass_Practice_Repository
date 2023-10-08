package com.fragment0902

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fragment0902.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val imgFragment = ImageFragment()
    val itemFragment = ItemFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initlayout()
    }

    private fun initlayout() {
    val fragment  = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.frameLayout,imgFragment)
        fragment.commit()

        binding.apply {
            button1.setOnClickListener{
            if(!imgFragment.isVisible){
                val fragment  = supportFragmentManager.beginTransaction()
                fragment.addToBackStack(null)
                fragment.replace(R.id.frameLayout,imgFragment)
                fragment.commit()
            }
            }

            button2.setOnClickListener {
                if(!itemFragment.isVisible){
                    val fragment  = supportFragmentManager.beginTransaction()
                    fragment.addToBackStack(null)
                    fragment.replace(R.id.frameLayout,itemFragment)
                    fragment.commit()
                }
            }

        }

    }
}