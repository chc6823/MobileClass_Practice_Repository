package com.example.chheomidtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val items:ArrayList<MyData>) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick()
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val nameedittext = itemView.findViewById<EditText>(R.id.nameedittext)
        val companyedittext = itemView.findViewById<EditText>(R.id.companyedittext)
        val telephoneedittext = itemView.findViewById<EditText>(R.id.telephonenumberedittext)
        init {
           nameedittext.setOnClickListener{
                itemClickListener?.OnItemClick()
            }
            companyedittext.setOnClickListener{
                itemClickListener?.OnItemClick()
            }
            telephoneedittext.setOnClickListener{
                itemClickListener?.OnItemClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.raw,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){holder.telephoneedittext
    }
    override fun getItemCount(): Int {
        return items.size
    }
}