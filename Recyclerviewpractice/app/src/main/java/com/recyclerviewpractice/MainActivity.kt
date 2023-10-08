package com.recyclerviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    var data:ArrayList<Mydata> = ArrayList()
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initRecylerview()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1,menu)
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuitem1 -> {
                recyclerView.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false)
            }
            R.id.menuitem2 -> {
                recyclerView.layoutManager = GridLayoutManager(this,
                   3)
            }
            R.id.menuitem3 -> {
                recyclerView.layoutManager = StaggeredGridLayoutManager(3,
                    StaggeredGridLayoutManager.VERTICAL)
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun initRecylerview() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = MyAdapter(data)
    }

    private fun initData() {
        data.add(Mydata("item1",10))
        data.add(Mydata("item2",8))
        data.add(Mydata("item3",15))
        data.add(Mydata("item4",6))
        data.add(Mydata("item5",10))
        data.add(Mydata("item6",7))
        data.add(Mydata("item7",10))
        data.add(Mydata("item8",19))
        data.add(Mydata("item9",10))
        data.add(Mydata("item10",22))
        data.add(Mydata("item11",10))
        data.add(Mydata("item12",14))
    }
}