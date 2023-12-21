package com.example.billgenerator.ui.adapter

// src/main/kotlin/com/example/myapplication/BillAdapter.kt
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.billgenerator.R
import com.example.roomdatabase.data.entity.bill1.BillEntity1

class AllBillAdapter(
    private val bills: List<BillEntity1>,
    private val onIcon1Click: (BillEntity1) -> Unit,
    private val onIcon2Click: (BillEntity1) -> Unit,
    private val onIcon3Click: (BillEntity1) -> Unit
) : RecyclerView.Adapter<AllBillAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tabText: TextView = itemView.findViewById(R.id.billDescriptionName)
        val icon1: ImageView = itemView.findViewById(R.id.viewIcon)
        val icon2: ImageView = itemView.findViewById(R.id.editIcon)
        val icon3: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_bill_row, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBill = bills[position]

        holder.tabText.text = "Bill No #${currentBill.billNo}"

        holder.icon1.setOnClickListener { onIcon1Click(currentBill) }
        holder.icon2.setOnClickListener { onIcon2Click(currentBill) }
        holder.icon3.setOnClickListener { onIcon3Click(currentBill) }
    }

    override fun getItemCount(): Int {
        return bills.size
    }
}
