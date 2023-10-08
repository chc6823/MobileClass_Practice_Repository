package com.chfinaltest

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView

class MyDBHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        val DB_NAME = "mydb.db"
        val DB_VERSION = 1
        val TABLE_NAME = "users"
        val USERID = "userid"
        val USERNAME = "username"
        val TEL = "telephone"
    }

    fun getALLRecord(){
        val strsql = "select * from $TABLE_NAME;"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql,null)
        //showRecord(cursor)
        cursor.close()
        db.close()
    }

    fun insertProduct(users : Users):Boolean{
        val values = ContentValues()
        values.put(USERID,users.name)
        values.put(USERNAME,users.name)
        values.put(TEL,users.tel)
        val db = writableDatabase
        val flag = db.insert(TABLE_NAME,null,values)>0 //return -1 if fails
        db.close()
        return flag
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val create_table = "create table if not exists $TABLE_NAME("+
                "$USERID integer primary key autoincrement,"+
                "$USERNAME text,"+
                "$TEL text);"
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val drop_table = "drop table if exists $TABLE_NAME;"
        db!!.execSQL(drop_table)
        onCreate(db)
    }
    private fun showRecord(cursor: Cursor) {
        cursor.moveToFirst()
        val attrcount = cursor.columnCount
        val activity = context as MainActivity
        activity.binding.frameLayout.removeAllViewsInLayout()
        // making title
        val tablerow = TableRow(activity)
        val rowParam = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT)
        tablerow.layoutParams = rowParam
        val viewParam = TableRow.LayoutParams(0,100,1f)
        for(i in 0 until attrcount){
            val textview = TextView(activity)
            textview.layoutParams = viewParam
            textview.text = cursor.getColumnName(i)
            textview.setBackgroundColor(Color.LTGRAY)
            textview.textSize = 15.0f
            textview.gravity = Gravity.CENTER
            tablerow.addView(textview)
        }
        activity.binding.frameLayout.addView(tablerow)
        if(cursor.count==0) return
        //adding record
        do {
            val row = TableRow(activity)
            row.layoutParams = rowParam
            row.setOnClickListener{
                for(i in 0 until attrcount){
                    val textView = row.getChildAt(i) as TextView
                    when(textView.tag){
                        0-> activity.binding.userIdEdit.setText(textView.text)
                        1-> activity.binding.userNameEdit.setText(textView.text)
                        2-> activity.binding.userTelEdit.setText(textView.text)
                    }
                }
            }
            for(i in 0 until attrcount){
                val textview = TextView(activity)
                textview.layoutParams = viewParam
                textview.tag = i
                textview.text = cursor.getString(i)
                textview.setBackgroundColor(Color.LTGRAY)
                textview.textSize = 15.0f
                textview.gravity = Gravity.CENTER
                row.addView(textview)
            }
            activity.binding.frameLayout.addView(row)
        }while (cursor.moveToNext())
    }

}