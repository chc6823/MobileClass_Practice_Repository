package com.chfinaltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chfinaltest.MainActivity as MainActivity1

class TableFragment : Fragment() {
    var myDBHelper = MyDBHelper(this@TableFragment)
    var binding: FragmentTableBinding?=null
    val myViewModel:MyViewModel by activityViewModels()
    var data = arrayListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentTextBinding.inflate(layoutInflater,container,false)
        return binding!!.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}