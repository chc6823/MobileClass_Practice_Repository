package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAddVoc2Binding
import com.example.myapplication.databinding.ActivityIntroBinding
import java.io.PrintStream

class AddVocActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddVoc2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVoc2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
     binding.apply{
         button3.setOnClickListener{
            val word = word.text.toString()
             val meaning = meaning.text.toString()
             writefile(word,meaning)

         }
         button4.setOnClickListener{
            setResult(Activity.RESULT_CANCELED)
             finish()
         }
     }
    }

    private fun writefile(word : String,meaning:String){
        val output = PrintStream(openFileOutput("out.txt", Context.MODE_APPEND))
        output.println(word)
        output.println(meaning)
        output.close()
        intent.putExtra("voc",MyData(word,meaning))
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}