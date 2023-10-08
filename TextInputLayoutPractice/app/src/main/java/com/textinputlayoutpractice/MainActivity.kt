package com.textinputlayoutpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLayout()
    }

    private fun initLayout() {
        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val emailtext = findViewById<TextInputEditText>(R.id.emailtext)
        emailtext.addTextChangedListener {
            if(it.toString().contains('@')){
                textInputLayout.error = null
            }
            else
            {
                textInputLayout.error = "이메일 형식이 올바르지 않습니다."
            }
        }
    }
}