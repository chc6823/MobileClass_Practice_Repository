package com.layoutpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    val checkid = intArrayOf(
        R.id.checkbox1, R.id.checkbox2, R.id.checkbox3, R.id.checkbox4,
        R.id.checkbox5, R.id.checkbox6, R.id.checkbox7,
        R.id.checkbox8, R.id.checkbox9, R.id.checkbox10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLayout()
    }

    private fun initLayout() {
        for (id in checkid) {
            val checkbox = findViewById<CheckBox>(id)
            checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->
                val imgid = resources.getIdentifier(compoundButton.text.toString(),"id",packageName)
                val imgview = findViewById<ImageView>(imgid)
                if(isChecked)
                    imgview.visibility = View.VISIBLE
                else
                    imgview.visibility = View.INVISIBLE
            }
        }
    }
}