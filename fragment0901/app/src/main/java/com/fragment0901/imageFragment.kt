package com.fragment0901

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.fragment0901.databinding.FragmentImageBinding

class imageFragment : Fragment() {
    var binding:FragmentImageBinding?=null
    val myViewModel:MyViewModel by activityViewModels()
    val imglist = arrayListOf<Int>(R.drawable.p1,R.drawable.p2,R.drawable.p3)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentImageBinding.inflate(layoutInflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.apply {
            imageView2.setOnClickListener {
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val intent = Intent(activity,SecondActivity::class.java)
                intent.putExtra("imgnum",myViewModel.selectnum.value)
                startActivity(intent)
            }
            }
            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    R.id.radioButton1 -> {
                     myViewModel.setLiveData(0)
                    }
                    R.id.radioButton2 -> {
                        myViewModel.setLiveData(1)
                    }
                    R.id.radioButton3 -> {
                        myViewModel.setLiveData(2)
                    }
                }
                imageView2.setImageResource(imglist[myViewModel.selectnum.value!!])
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}