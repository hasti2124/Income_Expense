package com.example.incomeexpense.Fragment

import android.R
import android.graphics.Color
import android.graphics.Paint.Align
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.incomeexpense.databinding.FragmentReportBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class ReportFragment : Fragment() {
    lateinit var binding: FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater)
        val list = ArrayList<PieEntry>()
        list.add(PieEntry(30f,"Expence"))
        list.add(PieEntry(80f,"Income"))

        val pieDataSet = PieDataSet(list,"")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextSize=15f
        pieDataSet.valueTextColor= Color.BLACK

        var pieData = PieData(pieDataSet)
        binding.pieChart.data = pieData
        binding.pieChart.description.text = "Pie Chart Of Transaction"
        binding.pieChart.centerText = "Transaction List"
        binding.pieChart.animateY(1300)

        return binding.root

    }

}