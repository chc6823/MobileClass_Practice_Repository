package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RowBinding

class MyAdapter(val items:ArrayList<MyData>) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick(data: MyData,position: Int)
    }

    fun moveItem(oldposition:Int,newposition:Int){
    val item = items[oldposition]
        items.removeAt(oldposition)
        items.add(newposition,item)
        notifyItemMoved(oldposition,newposition)
    }

    fun removeItem(position: Int){
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root){
        //val textView = itemView.findViewById<TextView>(R.id.textView)
        //val meaningView = itemView.findViewById<TextView>(R.id.meaningView)
        init {
            binding.textView.setOnClickListener{
                itemClickListener?.OnItemClick(items[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        val binding = RowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView.text = items[position].word
        holder.binding.meaningView.text = items[position].meaning
        if(!items[position].isOpen)
            holder.binding.meaningView.visibility = View.GONE
        else
            holder.binding.meaningView.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return items.size
    }
}