package com.moppexample

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moppexample.databinding.RowBinding

class MyAdapter(val items:ArrayList<MyData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(data: MyData,position: Int)
    }
    var itemClickListener:OnItemClickListener?=null
    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.imageview.setOnClickListener{
                itemClickListener?.OnItemClick(items[adapterPosition],adapterPosition)
            }
            binding.name.setOnClickListener{
                itemClickListener?.OnItemClick(items[adapterPosition],adapterPosition)
            }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {

        val result = Intent()
        val merchandise = result.data?.getSerializableExtra("merchandise") as MyData
        for(data in items){
            if((result == data[position].name) || (result == data.tag)){//이름 또는 태그 일치하면
                //mainactivity에 ok신호 보내고
                setResult(RESULT_OK,result)
                //뷰홀더에 등록
                setResult()
            }
        }
        holder.binding.imageview =
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