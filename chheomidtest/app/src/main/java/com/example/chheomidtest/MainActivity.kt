package com.example.chheomidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var recyclerView: RecyclerView
        val data:ArrayList<MyData> = ArrayList()
        lateinit var adapter: MyAdapter
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(data)
        adapter.itemClickListener = object : MyAdapter.OnItemClickListener {
            override fun OnItemClick(data: Mydata) {

            }
        }
    }
}