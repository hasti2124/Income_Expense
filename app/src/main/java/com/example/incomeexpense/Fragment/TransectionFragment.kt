package com.example.incomeexpense.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incomeexpense.Adapter.TransListAdapter
import com.example.incomeexpense.DataBase.DbHelper
import com.example.incomeexpense.ModelClass.TransModel
import com.example.incomeexpense.databinding.FragmentTransectionBinding
import com.example.incomeexpense.databinding.UpdateDialogBinding

class TransectionFragment : Fragment() {
    var transList = ArrayList<TransModel>()
    lateinit var DbHelper : DbHelper
    lateinit var binding: FragmentTransectionBinding
    lateinit var bind: UpdateDialogBinding
    var isExpense = 0
    lateinit var adapter : TransListAdapter
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransectionBinding.inflate(layoutInflater)
        DbHelper = DbHelper(context)
        transList = DbHelper.getTrans()
       updateTotal(transList)
       transList = transList.reversed() as ArrayList<TransModel>

       adapter = TransListAdapter({
           updateDialog(it)
       },{
           deleteTrans(it)
       })
       adapter.setTrans(transList)

       binding.rcvTransList.layoutManager = LinearLayoutManager(context)
       binding.rcvTransList.adapter = adapter
        return binding.root
    }

    private fun deleteTrans(it: Int) {
        var dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete Transection")
            .setMessage("Are you sure to delete this Transection")
            .setPositiveButton("Yes",object  : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    DbHelper.deleteTrans(it)
                    try {
                        adapter.updateData(DbHelper.getTrans().reversed() as ArrayList<TransModel>)
                    }catch (e:Exception){

                    }

                }
            }).setNegativeButton("No",object  : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }

            }).create()
        dialog.show()
    }

//    fun updateTotal(transModel: ArrayList<TransModel>) {
//        var totalIncome = 0
//        var totalExpense = 0
//        for (trans in transList) {
//            if (trans.isExpense == 0) {
//                totalIncome += trans.amount
//            } else {
//                totalExpense += trans.amount
//            }
//        }
//        binding.txtIncome.text = totalIncome.toString()
//        binding.txtExpense.text = totalExpense.toString()
//        binding.txtAllover.text = (totalIncome - totalExpense).toString()
//
//    }

    fun updateTotal(transModel: ArrayList<TransModel>){
        var totalIncome =0
        var totalExpense =0
        for(trans in transList){
            if (trans.isExpense == 0){
                totalIncome += trans.amount
            }else{
                totalExpense += trans.amount
            }
        }
        binding.txtIncome.text = totalIncome.toString()
        binding.txtExpense.text = totalExpense.toString()
    }

    private fun updateDialog(transModel: TransModel) {
        var dialog = Dialog(requireContext())
        var bind = UpdateDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.edtAmount.setText(transModel.amount.toString())
        bind.edtCategory.setText(transModel.category)
        bind.edtNote.setText(transModel.note)

        bind.btnSubmit.setOnClickListener {
            var amount = bind.edtAmount.text.toString().toInt()
            var category = bind.edtCategory.text.toString()
            var note = bind.edtNote.text.toString()
            var model = TransModel(transModel.id,amount,category,note,transModel.isExpense)
            DbHelper.updateTrans(model)
            dialog.dismiss()
            adapter.updateData(DbHelper.getTrans().reversed() as ArrayList<TransModel>)
        }

        dialog.show()
    }


}