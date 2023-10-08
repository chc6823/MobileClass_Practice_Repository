package com.chfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chfinaltest.databinding.ActivityMainBinding
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var myDBHelper: MyDBHelper
    val myviewmodel : MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDb()
        init()
        getALLRecord()
        setContentView(binding.root)
    }
    fun getALLRecord(){
        myDBHelper.getALLRecord()
    }
    fun clearEditText(){
        binding.apply {
            userIdEdit.text.clear()
            userNameEdit.text.clear()
            userTelEdit.text.clear()
        }
    }
    private fun initDb() {
        val dbfile = getDatabasePath("mydb.db")
        if(dbfile.parentFile.exists())
        {
            dbfile.parentFile.mkdir()
        }
        if(!dbfile.exists()){
            val file = resources.openRawResource(R.raw.mydb)
            val fileSize = file.available()
            val buffer = ByteArray(fileSize)
            file.read(buffer)
            file.close()
            dbfile.createNewFile()
            val output = FileOutputStream(dbfile)
            output.write(buffer)
            output.close()
        }
    }

    private fun init(){
        myDBHelper = MyDBHelper(this)
        binding.apply {
            insertbtn.setOnClickListener {
                val userid = userIdEdit.text.toString()
                val name = userNameEdit.text.toString()
                val tel  = userTelEdit.text.toString()
                val users = Users(userid,name,tel)
                val result = myDBHelper.insertProduct(users)
                if(result){
                    getALLRecord()
                    Toast.makeText(this@MainActivity,"Insert Success",
                        Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity,"Insert Failed",
                        Toast.LENGTH_SHORT).show()
                }
                clearEditText()
            }

        }

    }
}