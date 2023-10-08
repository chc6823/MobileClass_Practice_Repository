package com.jsoup1002

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.icu.text.ConstrainedFieldPosition
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsoup1002.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.parser.Parser

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: MyAdapter
    val url : String = "https://www.daum.net"
    val rssurl = "http://fs.jtbc.joins.com//RSS/politics.xml" //보안상 xml은 https만 가능
    val scope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun getrssnews(){
        scope.launch {
            adapter.items.clear()
            val doc  = Jsoup.connect(rssurl).parser(Parser.xmlParser()).get()
            val headlines = doc.select(
                "item")
            for(news in headlines){
                adapter.items.add(MyData(news.select("title").text(),
                    news.select("link").text()))
            }
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
                binding.swipe.isRefreshing = false
            }
        }
    }

    fun getnews(){
        scope.launch {
            adapter.items.clear()
            val doc  = Jsoup.connect(url).get()
            val headlines = doc.select(
                "ul.list_newsissue>li>div.item_issue>div>strong.tit_g>a")
            for(news in headlines){
                adapter.items.add(MyData(news.text(),news.absUrl("href")))
            }
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
                binding.swipe.isRefreshing = false
            }
        }
    }
    fun init(){
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            //getnews()
            getrssnews()
        }
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        adapter = MyAdapter(ArrayList<MyData>())
        adapter.itemClickListner = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(position: Int){
                val intent = Intent(ACTION_VIEW, Uri.parse(adapter.items[position].url))
                startActivity(intent)
            }
        }
        binding.recyclerView.adapter = adapter
        getnews()
        getrssnews()
    }
}