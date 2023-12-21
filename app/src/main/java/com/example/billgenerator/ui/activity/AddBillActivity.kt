package com.example.billgenerator.ui.activity

import RowPopupFragment
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.billgenerator.databinding.ActivityAddBillBinding
import com.example.billgenerator.ui.adapter.AddBillRowAdapter
import com.example.billgenerator.ui.fragment.RowInputFragment
import com.example.billgenerator.ui.interf.RowBillInterface
import com.example.roomdatabase.viewmodel.BillViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("NotifyDataSetChanged", "NotifyDataSetChanged", "SimpleDateFormat")
class AddBillActivity : AppCompatActivity(), RowBillInterface {

    private lateinit var binding: ActivityAddBillBinding

    //    private lateinit var viewModel: BillViewModel
    private lateinit var date: Date
    private var billNo = "#000"

    @Inject
    lateinit var viewModel: BillViewModel

    //    private val viewModel: BillViewModel by viewModels()
    private var billsList: MutableList<BillData1> = mutableListOf()

    private val billAdapter = AddBillRowAdapter(billsList,
        { bill -> viewRowBill(bill) },
        { bill -> deleteRowBill(bill) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val billDao = MyApplication.database.billDao()
//        val billRepository = BillRepositoryImpl(billDao)
//        val viewModelFactory = BillViewModelFactory(billRepository)
//
//        viewModel = ViewModelProvider(this, viewModelFactory)[BillViewModel::class.java]

        supportActionBar?.apply {
            title = "Bill No $billNo"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.billNoText.addTextChangedListener {
            supportActionBar?.title = "Bill No #${it.toString()}"
        }

        binding.openDatePickerButton.setOnClickListener {
            showDatePicker()
        }

        binding.billAddButton.setOnClickListener {
            Log.d("AddBillActivity", date.toString())
        }

        binding.billAddRowFloatingButton.setOnClickListener {
            showPopupFragment(RowInputFragment(), "row_input_fragment")
        }


//        RecyclerView for the bill description row
        val recyclerView = binding.addBillRowRV

        recyclerView.adapter = billAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.billAddButton.setOnClickListener {
            showAlertDialog(this, "Are you sure you want to save this bill?")
        }
    }

    private fun insertBill(context: Context) {
        val billNo = binding.billNoText.text.toString()
        val fromAddress = binding.billFromAddressText.text.toString()
        val toAddress = binding.billToAddressText.text.toString()

        // Validate inputs
        if (billNo.isEmpty() || fromAddress.isEmpty() || toAddress.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            // Try to convert billNo to an integer
            val billNoInt = billNo.toInt()

            // Create a BillEntity1 object
            val bill = BillEntity1(
                0,
                "user1",
                billNoInt,
                date.toString(),
                fromAddress,
                toAddress,
                billsList
            )
            viewModel.insertBills(bill)
            val intent = Intent(this, ShowBillActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error inserting the bill", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun viewRowBill(bill: BillData1) {
        val fragment = RowPopupFragment.newInstance(bill)
        fragment.show(supportFragmentManager, RowPopupFragment.TAG)
    }

    private fun deleteRowBill(bill: BillData1) {
        billsList.remove(bill)
        billAdapter.notifyDataSetChanged()
    }

    private fun showPopupFragment(fragment: DialogFragment, myTag: String) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragment.show(fragmentManager, myTag)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun showAlertDialog(
        context: Context,
        title: String
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setPositiveButton("Insert") { _, _ ->
            insertBill(context)
        }

        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    private fun showDatePicker(): Date {
        val builder = MaterialDatePicker.Builder.datePicker()

        val datePicker = builder.build()
        datePicker.show(supportFragmentManager, datePicker.toString())

        var selectedDate: Long = 0

        datePicker.addOnPositiveButtonClickListener { selectedDateInMillis ->
            selectedDate = selectedDateInMillis

            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val formattedDate = dateFormatter.format(Date(selectedDate))
            binding.billDate.text = formattedDate
            date = Date(selectedDate)
        }

        return Date(selectedDate)
    }

    override fun rowBill(bill: BillData1) {
        billsList.add(bill)
        billAdapter.notifyDataSetChanged()
//        Log.d("mybills", billsList.toString() + "Bills are $bill")
    }
}