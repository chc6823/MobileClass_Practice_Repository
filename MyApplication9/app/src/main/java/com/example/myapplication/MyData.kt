package com.example.myapplication

import java.io.Serializable

//텍스트 내 정보 저장
//https://codechacha.com/ko/data-classes-in-kotlin/ -> data class 글
data class MyData(var word: String, var meaning:String,var isOpen:Boolean=false):Serializable