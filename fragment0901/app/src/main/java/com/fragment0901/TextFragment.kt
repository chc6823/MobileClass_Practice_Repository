package com.fragment0901

import android.app.Fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.fragment0901.databinding.FragmentImageBinding
import com.fragment0901.databinding.FragmentTextBinding


class TextFragment : Fragment() {

        var binding: FragmentTextBinding?=null
        val myViewModel:MyViewModel by activityViewModels()
        var data = arrayListOf<String>("ImageData1","ImageData2","ImageData3")
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            binding  = FragmentTextBinding.inflate(layoutInflater,container,false)
            return binding!!.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val i = requireActivity().intent
        val imgNum = i.getIntExtra("imgNum",-1)
        if(imgNum != -1){
            binding!!.textview.text = data[imgNum]
        }else{
            myViewModel.selectnum.observe(viewLifecycleOwner, Observer {
                binding!!.textview.text = data[it]
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}