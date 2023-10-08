package com.sqlite1102

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
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
        val TABLE_NAME = "products"
        val PID = "pid"
        val PNAME = "pname"
        val PQUANTITY = "pquantity"
    }
    fun getALLRecord(){
        val strsql = "select * from $TABLE_NAME;"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql,null)
        showRecord(cursor)
        cursor.close()
        db.close()
    }

    private fun showRecord(cursor: Cursor) {
        cursor.moveToFirst()
        val attrcount = cursor.columnCount
        val activity = context as MainActivity
        activity.binding.tablelayout.removeAllViewsInLayout()
        // making title
        val tablerow = TableRow(activity)
        val rowParam = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
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
        activity.binding.tablelayout.addView(tablerow)
        if(cursor.count==0) return
        //adding record
        do {
            val row = TableRow(activity)
            row.layoutParams = rowParam
            row.setOnClickListener{
                for(i in 0 until attrcount){
                val textView = row.getChildAt(i) as TextView
                    when(textView.tag){
                        0-> activity.binding.pIdedit.setText(textView.text)
                        1-> activity.binding.pNameedit.setText(textView.text)
                        2-> activity.binding.pQuantityedit.setText(textView.text)
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
            activity.binding.tablelayout.addView(row)
        }while (cursor.moveToNext())
    }

    fun insertProduct(product : Product):Boolean{
        val values = ContentValues()
        values.put(PNAME,product.pName)
        values.put(PQUANTITY,product.pQuantity)
        val db = writableDatabase
        val flag = db.insert(TABLE_NAME,null,values)>0 //return -1 if fails
        db.close()
       return flag
    }
    override fun onCreate(db: SQLiteDatabase?) { //db생성시 호출
        val create_table = "create table if not exists $TABLE_NAME("+
                "$PID integer primary key autoincrement,"+
                "$PNAME text,"+
                "$PQUANTITY integer);"
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {//db version changed
        val drop_table = "drop table if exists $TABLE_NAME;"
        db!!.execSQL(drop_table)
        onCreate(db)
    }
    //"select * from product where NAME = pid"
    fun findProduct(name: String): Boolean {
        val strsql = "select * from $TABLE_NAME where $PNAME = '$name';"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql,null)
        val flag = cursor.count!=0
        showRecord(cursor)
        cursor.close()
        db.close()
        return flag
    }
    //"delete * from product where NAME = pid"
    fun DeleteProduct(pid: String): Boolean {
        val strsql = "select * from $TABLE_NAME where $PID = '$pid';"
        val db = writableDatabase
        val cursor = db.rawQuery(strsql,null)
        val flag = cursor.count!=0
        if(flag){
            cursor.moveToFirst()
            db.delete(TABLE_NAME,"$PID=?", arrayOf(pid))
        }
        cursor.close()
        db.close()
        return flag
    }
    //"update * from product where NAME = pid"
    fun updateProduct(product: Product): Boolean {
        val pid = product.pId
        val strsql = "select * from $TABLE_NAME where $PID = '$pid';"
        val db = writableDatabase
        val cursor = db.rawQuery(strsql,null)
        val flag = cursor.count!=0
        if(flag){
            cursor.moveToFirst()
            val values = ContentValues()
            values.put(PNAME,product.pName)
            values.put(PQUANTITY,product.pQuantity)
            db.update(TABLE_NAME,values,"$PID=?", arrayOf(pid.toString()) )
        }
        cursor.close()
        db.close()
        return flag
    }
    //"select * from product where pname like '김%'"
    fun findProduct2(name: String): Boolean {
        val strsql = "select * from $TABLE_NAME where $PNAME like '$name%';"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql,null)
        val flag = cursor.count!=0
        showRecord(cursor)
        cursor.close()
        db.close()
        return flag
    }


}