package com.example.incomeexpense.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incomeexpense.Adapter.TransListAdapter
import com.example.incomeexpense.DataBase.DbHelper
import com.example.incomeexpense.ModelClass.TransModel
import com.example.incomeexpense.R
import com.example.incomeexpense.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    var transList = ArrayList<TransModel>()
    lateinit var dbHelper: DbHelper
    lateinit var binding: FragmentProfileBinding
    lateinit var adapter : TransListAdapter
    var isExpense = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentProfileBinding.inflate(layoutInflater)
        dbHelper = DbHelper(context)
        transList = dbHelper.getTrans()
        updateTotal(transList)
        adapter = TransListAdapter({},{})
        adapter.setTrans(transList)

        binding.rcvProfile.layoutManager = LinearLayoutManager(context)
        binding.rcvProfile.adapter = adapter

        return binding.root
    }

    fun updateTotal(transModel: ArrayList<TransModel>){
        var totalIncome = 0
        var totalExpense = 0
        for(trans in transList){
            if (trans.isExpense == 0){
                totalIncome += trans.amount
            }else{
                totalExpense += trans.amount
            }
        }
        binding.txtAllover.text = (totalIncome-totalExpense).toString()
    }

}