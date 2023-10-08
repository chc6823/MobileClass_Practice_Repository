package com.volley1001

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.volley1001.databinding.ActivityMainBinding
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var  requestQueue : RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }
    fun initLayout(){
        requestQueue = Volley.newRequestQueue(this)
        binding.apply{
            button.setOnClickListener {
                progressbar.visibility = View.VISIBLE
                val stringRequest = StringRequest(
                    Request.Method.GET,
                    editText.text.toString(),
                    Response.Listener<String>{
                        textView.text = String(it.toByteArray(Charsets.ISO_8859_1),Charsets.UTF_8)
                        //textView.text = it
                        progressbar.visibility = View.GONE
                    },
                    Response.ErrorListener {
                        textView.text = it.message
                    }
                )
                requestQueue.add(stringRequest)
            }
        }
    }
}