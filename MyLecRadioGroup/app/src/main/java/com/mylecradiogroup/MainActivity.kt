package com.mylecradiogroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val imageView = findViewById<ImageView>(R.id.imageView2)
        radioGroup.setOnCheckedChangeListener{radioGroup,checkedID ->
            when(checkedID){
                R.id.radioButton1 -> imageView.setImageResource(R.drawable.p1)
                R.id.radioButton2 -> imageView.setImageResource(R.drawable.p2)
                R.id.radioButton3 -> imageView.setImageResource(R.drawable.p3)
            }
        }
    }
}