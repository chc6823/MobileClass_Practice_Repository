package com.jsoup1002

import android.inputmethodservice.Keyboard
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsoup1002.databinding.RowBinding

class MyAdapter(val items:ArrayList<MyData>)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    interface OnItemClickListener{
        fun OnItemClick(position: Int)
    }
    var itemClickListner : OnItemClickListener?=null

    inner class MyViewHolder(val binding : RowBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.newstitle.setOnClickListener {
                itemClickListner?.OnItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
       holder.binding.newstitle.text = items[position].newstitle
    }

    override fun getItemCount(): Int {
        return items.size
    }


}