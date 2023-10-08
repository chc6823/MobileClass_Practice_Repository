package com.webview1001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.webview1001.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        binding.apply{
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.settings.builtInZoomControls = true
            webView.settings.defaultTextEncodingName = "utf-8"
            webView.loadUrl("https://www.google.com")

        }
    }
}