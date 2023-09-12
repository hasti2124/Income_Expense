package com.example.incomeexpense.Fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import com.example.incomeexpense.DataBase.DbHelper
import com.example.incomeexpense.ModelClass.TransModel
import com.example.incomeexpense.databinding.FragmentAmountBinding


class AmountFragment : Fragment() {

    lateinit var binding: FragmentAmountBinding
    var isExpense = 0
    lateinit var dbHelper : DbHelper
    var date = ""
    var time = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAmountBinding.inflate(layoutInflater)
        dbHelper = DbHelper(context)

        initView()

        return binding.root
    }

    private fun initView() {
        binding.cardIncome.setOnClickListener {
            isExpense = 0
            binding.txtincome.setTextColor(Color.parseColor("#FFFFFF"))
            binding.txtexpense.setTextColor(Color.parseColor("#000000"))
            binding.cardIncome.setCardBackgroundColor(Color.parseColor("#8584C3"))
            binding.cardExpense.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        binding.cardExpense.setOnClickListener {
            isExpense = 1
            binding.txtincome.setTextColor(Color.parseColor("#000000"))
            binding.txtexpense.setTextColor(Color.parseColor("#FFFFFF"))
            binding.cardIncome.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.cardExpense.setCardBackgroundColor(Color.parseColor("#8584C3"))
        }
        binding.btnSubmit.setOnClickListener {
            var amount = binding.edtAmount.text.toString().toInt()
            var category = binding.edtCategory.text.toString()
            var note = binding.edtNote.text.toString()
            var model = TransModel(1,amount,category,note,isExpense)
            dbHelper.addTrans(model)
            binding.edtAmount.setText("")
            binding.edtCategory.setText("")
            binding.edtNote.setText("")
        }
        binding.selectDate.setOnClickListener {

            var dialog = DatePickerDialog(requireContext(),object :DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    date = "$p3/${(p2+1)}/$p1"
                }

            },2023,7,23)
            dialog.show()
        }
        binding.txttime.setOnClickListener {
            var dialog = TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        time = "$p1:$p2"
                    }

                }, 12, 5, true)
            dialog.show()
        }
//        fun Date.toString(format: String, locale: Locale = Locale.getDefault()):String{
//            val formatter = SimpleDateFormat(format,locale)
//            return formatter.format(this)
//        }
//        fun getCurrentDate():Date{
//            return  Calendar.getInstance().time
//        }

    }

}