package com.implicitintentpractice

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.implicitintentpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val CALL_REQUEST = 100

    val permissions = arrayOf(android.Manifest.permission.CALL_PHONE,
        android.Manifest.permission.CAMERA)

    val multiplePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            val resultPermission = it.all{
                it.value == true
            }
            if(resultPermission)  { //모든권한 승인
                Toast.makeText(this,"권한이 승인되었습니다.",Toast.LENGTH_SHORT).show()
                callAction()
            }else{
                Toast.makeText(this,"권한이 거절되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        // setContentView(binding.root)
        initLayout()
    }
    private fun callAlertDlg(){
    val builder = AlertDialog.Builder(this)
        builder.setMessage("PHONE_CALL,CAMERA 권한이 필요합니다.")
            .setTitle("권한 체크")
            .setPositiveButton("OK"){
                _,_ ->
                multiplePermissionLauncher.launch(permissions)
//                ActivityCompat.requestPermissions(this,
//                arrayOf(android.Manifest.permission.CALL_PHONE),CALL_REQUEST)
            }
            .setNegativeButton("Cancel"){
                dlg,_,->dlg.dismiss()
            }
        val dlg = builder.create()
        dlg.show()
    }
    private fun callAction() {
        val number = Uri.parse("tel 010-1234-1234")
        val callIntent = Intent(Intent.ACTION_CALL,number)
        when{
            (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE) ==PackageManager.PERMISSION_GRANTED) &&
                    (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.CAMERA) ==PackageManager.PERMISSION_GRANTED)-> {
                        startActivity(callIntent)
                    }
            ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.CALL_PHONE) -> {
                    //refused
                    callAlertDlg()
                }
            else ->{
                multiplePermissionLauncher.launch(permissions)
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(android.Manifest.permission.CALL_PHONE),CALL_REQUEST)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CALL_REQUEST->{
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"권한이 승인되었습니다.",Toast.LENGTH_SHORT).show()
                callAction()
            }else{
                Toast.makeText(this,"권한이 거절되었습니다.",Toast.LENGTH_SHORT).show()
            }
            }
        }
    }

    private fun initLayout() {
        with(binding){
            callBtn.setOnClickListener {
                callAction()
            }
            msgBtn.setOnClickListener {
                val message = Uri.parse("sms:010-1234-1234")
                val messageIntent = Intent(Intent.ACTION_SENDTO,message)
                messageIntent.putExtra("sms_body","빨리 다음 꺼 하자...")
                startActivity(messageIntent)
            }
            webBtn.setOnClickListener {
                val webpage = Uri.parse("http://www.naver.com")
                val webIntent = Intent(Intent.ACTION_VIEW,webpage)
                startActivity(webIntent)
            }
            mapBtn.setOnClickListener {
                val location = Uri.parse("geo:37.543684,127.077130?z=16")
                val mapIntent = Intent(Intent.ACTION_VIEW,location)
                startActivity(mapIntent)
            }
        }
    }
}