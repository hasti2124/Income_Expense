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


class ReportFragment : Fragment() {
    lateinit var binding: FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater)
        return binding.root
    }

}