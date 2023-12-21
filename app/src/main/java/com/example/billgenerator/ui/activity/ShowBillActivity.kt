package com.example.billgenerator.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.billgenerator.R
import com.example.billgenerator.databinding.ActivityMainBinding
import com.example.billgenerator.databinding.ActivityShowBillBinding
import com.example.billgenerator.ui.adapter.AllBillAdapter
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.viewmodel.BillViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("MissingInflatedId", "SetTextI18n", "NotifyDataSetChanged")
@AndroidEntryPoint
class ShowBillActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowBillBinding

    @Inject
    lateinit var viewModel: BillViewModel

    private lateinit var billAdapter: AllBillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.allBillRV
        val swipeRefreshLayout = binding.swipeRefreshLayout

        val bills = viewModel.getBills()
        val context = this
        viewModel.bills.observe(this, Observer { data ->
            if (data.isEmpty()) {
                binding.noContentRV.text = "No bills found"
            } else {
                binding.noContentRV.text = ""
            }

            billAdapter = AllBillAdapter(data,
                { bill -> viewRowBill(bill) },
                { bill -> editRowBill(context, bill) },
                { bill -> deleteRowBill(context, bill) }
            )
            billAdapter.notifyDataSetChanged()
            Log.d("getBills", "Data received: $data")
            recyclerView.adapter = billAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getBills()
            swipeRefreshLayout.isRefreshing = false
        }

        binding.billAddButton.setOnClickListener {
            val intent = Intent(this, AddBillActivity::class.java)
            startActivity(intent)
        }
    }

    fun viewRowBill(bill: BillEntity1) {
        val intent = Intent(this, PreviewActivity::class.java)
        intent.putExtra("billEntity", bill)
        startActivity(intent)
    }

    fun viewRowBillBundle(bill: BillEntity1) {
        val bundle = Bundle()
        bundle.putParcelable("myBill", bill)
        val intent = Intent(this, PreviewActivity::class.java)
        intent.putExtra("bills", bundle)
    }

    fun editRowBill(context: Context, bill: BillEntity1) {
        val intent = Intent(this, EditBillActivity::class.java)
        intent.putExtra("billEntity", bill)
        startActivity(intent)
    }

    private fun deleteRowBill(context: Context, bill: BillEntity1) {
        showAlertDialog(context, bill, "Are you sure you want to delete?")
    }

    private fun deleteBill(context: Context, bill: BillEntity1) {
        viewModel.deleteBillById(bill.billId)
        Toast.makeText(context, "Bill No #${bill.billNo} has been deleted", Toast.LENGTH_LONG)
            .show()
        billAdapter.notifyDataSetChanged()
    }

    private fun showAlertDialog(
        context: Context,
        bill: BillEntity1,
        title: String,
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setPositiveButton("Delete") { _, _ ->
            deleteBill(context, bill)
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}