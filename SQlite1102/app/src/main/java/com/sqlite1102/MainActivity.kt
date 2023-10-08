package com.sqlite1102

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.sqlite1102.databinding.ActivityMainBinding
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var myDBHelper: MyDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDb()
        init()
        getALLRecord()
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

    fun getALLRecord(){
    myDBHelper.getALLRecord()
    }
    fun clearEditText(){
    binding.apply {
        pIdedit.text.clear()
        pNameedit.text.clear()
        pQuantityedit.text.clear()
        }
    }
    private fun init(){
        myDBHelper = MyDBHelper(this)
        binding.apply {
            testsql.addTextChangedListener{
                val pname = it.toString()
                val result = myDBHelper.findProduct2(pname)
            }
            insertbtn.setOnClickListener {
                val name = pNameedit.text.toString()
                val quantity = pQuantityedit.text.toString().toInt()
                val product = Product(0,name,quantity)
                val result = myDBHelper.insertProduct(product)
                if(result){
                    getALLRecord()
                    Toast.makeText(this@MainActivity,"DATA Insert Success",
                        Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity,"DATA Insert Failed",
                        Toast.LENGTH_SHORT).show()
                }
                clearEditText()
            }
            findbtn.setOnClickListener {
                val name = pNameedit.text.toString()
                val result = myDBHelper.findProduct(name)
                if(result){
                    Toast.makeText(this@MainActivity,"Record found",
                        Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity,"No Match Found",
                        Toast.LENGTH_SHORT).show()
                }
            }
            deletebtn.setOnClickListener {
                val pid = pIdedit.text.toString()
                val result = myDBHelper.DeleteProduct(pid)
                if(result){
                    Toast.makeText(this@MainActivity,"Data deleted",
                        Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity,"Data delete failed",
                        Toast.LENGTH_SHORT).show()
                }
                getALLRecord()
                clearEditText()
            }
            updatebtn.setOnClickListener {
                val pid  = pIdedit.text.toString().toInt()
                val name = pNameedit.text.toString()
                val quantity = pQuantityedit.text.toString().toInt()
                val product = Product(pid,name,quantity)
                val result = myDBHelper.updateProduct(product)
                if(result){
                    getALLRecord()
                    Toast.makeText(this@MainActivity,"DATA Update Success",
                        Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity,"DATA Update Failed",
                        Toast.LENGTH_SHORT).show()
                }
                clearEditText()
            }
        }
    }

}