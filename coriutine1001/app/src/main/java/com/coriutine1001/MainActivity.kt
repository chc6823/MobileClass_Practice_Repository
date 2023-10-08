package com.coriutine1001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.coriutine1001.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    fun init(){
        binding.apply{
       //event handling
            button.setOnClickListener{
            scope.launch {
                progressbar.visibility = View.VISIBLE
                var data = ""
                //메인 쓰레드에서는 네트워크 접속 불가능
//                withContext(Dispatchers.IO){
//                    data = loadNetwork(URL(editText.text.toString()))
//                }
                CoroutineScope(Dispatchers.IO).async{
                    data = loadNetwork(URL(editText.text.toString()))
                }.await()
                textView.text = data
                progressbar.visibility = View.GONE
            }
            }
        }
    }

    fun loadNetwork(url:URL):String{
        var result = ""
        val connect = url.openConnection() as HttpURLConnection
        connect.connectTimeout = 4000
        connect.readTimeout = 4000
        connect.requestMethod = "GET"
        connect.connect()
        val responseCode = connect.responseCode
        if(responseCode == HttpURLConnection.HTTP_OK){
            result = streamToString(connect.inputStream)
        }
        return result
    }

    private fun streamToString(inputStream: InputStream): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line:String
        var result = ""
        try {
            do{
                line = bufferReader.readLine()
                if(line!=null){
                    result += line
                }
            }while(line!=null)
            inputStream.close()
        } catch (ex : Exception){
            Log.e("error","읽기 실패")
        }
        return result
    }
}

