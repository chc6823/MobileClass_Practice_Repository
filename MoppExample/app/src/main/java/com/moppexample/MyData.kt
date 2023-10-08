package com.moppexample

import android.widget.ImageView
import java.io.Serializable

data class MyData(var name:String, var tag:String, var price:Int, var img: Int) :Serializable
