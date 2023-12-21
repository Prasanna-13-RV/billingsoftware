package com.example.billgenerator.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.billgenerator.R
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.billgenerator.databinding.FragmentRowInputBinding
import com.example.billgenerator.ui.interf.RowBillInterface

class RowInputFragment : DialogFragment() {

    lateinit var binding: FragmentRowInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRowInputBinding.inflate(inflater, container, false)

        val closeButton = binding.billCancelButton
        closeButton.setOnClickListener {
            // Handle button click here
            dismiss() // This dismisses the dialog
        }

        binding.billAddButton.setOnClickListener {
            val description = binding.billDescriptionText.text
            val quantity = binding.billQuantityText.text
            val unitPrice = binding.billUnitPriceText.text
            val total = binding.billTotalText.text
            val singleBill = BillData1(
                0,
                description.toString(),
                quantity.toString(),
                unitPrice.toString(),
                total.toString()
            )
            val myInterface: RowBillInterface = activity as RowBillInterface
            myInterface.rowBill(singleBill)
            dismiss()
        }
        return binding.root
    }
}