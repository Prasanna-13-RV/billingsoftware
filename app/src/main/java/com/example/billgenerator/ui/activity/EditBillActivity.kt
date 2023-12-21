package com.example.billgenerator.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.billgenerator.databinding.ActivityEditBillBinding
import com.example.billgenerator.ui.adapter.AddBillRowAdapter
import com.example.billgenerator.ui.fragment.RowInputFragment
import com.example.billgenerator.ui.interf.RowBillInterface
import com.example.roomdatabase.viewmodel.BillViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class EditBillActivity : AppCompatActivity(), RowBillInterface {

    private lateinit var binding: ActivityEditBillBinding

    //    private lateinit var viewModel: BillViewModel
    private lateinit var date: Date
    private var billNo = "#000"

    //    private val viewModel: BillViewModel by viewModels()
    @Inject
    lateinit var viewModel: BillViewModel
    private var billsList: MutableList<BillData1> = mutableListOf()

    private val billAdapter = AddBillRowAdapter(billsList,
        { bill -> viewRowEditBill(bill) },
        { bill -> deleteRowEditBill(bill) })

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val billDao = MyApplication.database.billDao()
//        val billRepository = BillRepositoryImpl(billDao)
//        val viewModelFactory = BillViewModelFactory(billRepository)
//
//        viewModel = ViewModelProvider(this, viewModelFactory)[BillViewModel::class.java]

        supportActionBar?.apply {
            title = "Bill No $billNo"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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
            showPopupFragment()
        }


//        RecyclerView for the bill description row
        val recyclerView = binding.addBillRowRV

        recyclerView.adapter = billAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.billAddButton.setOnClickListener {
            showAlertDialog(this, "Are you sure you want to edit this bill?")
        }

        val receivedIntent = intent
        val receivedBillEntity1: BillEntity1? = receivedIntent.getParcelableExtra("billEntity")

        if (receivedBillEntity1 != null) {
//            Log.d("mybills", receivedBillEntity1.toString())
            binding.billNoText.setText(receivedBillEntity1.billNo.toString())
            binding.billFromAddressText.setText(receivedBillEntity1.fromAddress)
            binding.billToAddressText.setText(receivedBillEntity1.toAddress)
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val formattedDate = dateFormatter.format(Date(receivedBillEntity1.billingDate))
            binding.billDate.text = formattedDate
            date = Date(receivedBillEntity1.billingDate)
            // If you have a list of BillData1, you can update your adapter and the list accordingly
            billsList.clear()
            billsList.addAll(receivedBillEntity1.billDescription)
            billAdapter.notifyDataSetChanged()
            Log.d("mybills", receivedBillEntity1.toString())

        } else {
            // Handle the case where the BillEntity1 object is null
            Log.d("mybills", "No data")
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun editBill(context: Context) {
        val receivedIntent = intent
        val receivedBillEntity1: BillEntity1? = receivedIntent.getParcelableExtra("billEntity")

        if (receivedBillEntity1 != null) {
            try {
                val billId = receivedBillEntity1.billId
                val billNo = binding.billNoText.text.toString()
                val fromAddress = binding.billFromAddressText.text.toString()
                val toAddress = binding.billToAddressText.text.toString()

                // Validate inputs
                if (billNo.isEmpty() || fromAddress.isEmpty() || toAddress.isEmpty()) {
                    // Show an error message or handle the validation error appropriately
                    showToast("Please fill in all fields")
                    return
                }

                // Try to convert billNo to an integer
                val billNoInt = billNo.toInt()

                // Create a BillEntity1 object
                val bill = BillEntity1(
                    billId,
                    "user1",
                    billNoInt,
                    date.toString(),
                    fromAddress,
                    toAddress,
                    billsList
                )

                // Update the bill in the database using ViewModel
                viewModel.insertBills(bill)

                Log.d("mybills service", bill.toString())

                // Start MainActivity
                val intent = Intent(this, ShowBillActivity::class.java)
                startActivity(intent)

            } catch (e: NumberFormatException) {
                // Handle the case where billNo is not a valid integer
                showToast("Invalid bill number")
            } catch (e: Exception) {
                // Handle other potential exceptions during the editing process
                showToast("Error editing the bill")
                e.printStackTrace()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun viewRowEditBill(bill: BillData1) {
//        showPopupFragment(RowPopupFragment(), "row_pop_up")
        val fragment = RowPopupFragment.newInstance(bill)
        fragment.show(supportFragmentManager, RowPopupFragment.TAG)
    }

    private fun deleteRowEditBill(bill: BillData1) {
        // Handle the click event for icon2
        // Remove the item from the list
        billsList.remove(bill)
        // Notify the adapter about the change
        billAdapter.notifyDataSetChanged()
    }


    private fun showPopupFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val popupFragment = RowInputFragment()
        popupFragment.show(fragmentManager, "row_input_fragment")
    }

    private fun showAlertDialog(
        context: Context, title: String
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
//        alertDialogBuilder.setMessage(subTitle)

        // Positive button (Save)
        alertDialogBuilder.setPositiveButton("Edit") { _, _ ->
            // Handle the "Save" button click
            // Perform the save operation or any other action
            editBill(context)
        }

        // Negative button (Cancel)
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            // Handle the "Cancel" button click
            // Dismiss the dialog or perform any other action
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    @SuppressLint("SimpleDateFormat")
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