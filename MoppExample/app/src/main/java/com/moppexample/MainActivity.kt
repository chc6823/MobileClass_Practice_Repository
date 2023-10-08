package com.moppexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.moppexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter : MyAdapter
    var mydata : ArrayList<MyData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initdata()
        initlayout()
    }

    private fun initdata() {
        //데이터베이스로 관리 필요,web parsing으로
        mydata.add(MyData("통새우치즈버거","햄버거",2800,R.drawable.shrimpburger))
        mydata.add(MyData("점보4단샌드","샌드위치",2540,R.drawable.jumbo4sand))
        mydata.add(MyData("창녕양파불고기버거","햄버거",3200,R.drawable.onionbulgogi))
        mydata.add(MyData("확실한너비아니정식","샌드위치",2800,R.drawable.jungsik))
        mydata.add(MyData("에그스윗포테두툼샌드","샌드위치",2800,R.drawable.eggjunbosand))
    }

    private fun initlayout() {
        binding.mainbutton.setOnClickListener {
            //검색결과 액티비티로 전환,text 값 인텐트로 넘겨줌
            val intent = Intent(this,SelectActivity::class.java)
            val activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
                    if(it.resultCode==Activity.RESULT_OK){
                        intent.putExtra("merchandise",MyData())
                    }
                else{
                    Toast.makeText(this,"일치하는 상품이 없습니다.",Toast.LENGTH_LONG).show()
                    }
                }
            activityResultLauncher.launch(intent)
        }
    }
}