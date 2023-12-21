package com.example.billgenerator.ui.adapter

// src/main/kotlin/com/example/myapplication/BillAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.billgenerator.R
import com.example.roomdatabase.data.entity.bill1.BillData1

class AddBillRowAdapter(
    private val bills: List<BillData1>,
    private val onIcon1Click: (BillData1) -> Unit,
    private val onIcon2Click: (BillData1) -> Unit
) : RecyclerView.Adapter<AddBillRowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tabText: TextView = itemView.findViewById(R.id.billDescriptionName)
        val icon1: ImageView = itemView.findViewById(R.id.viewIcon)
        val icon2: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_bill_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBill = bills[position]

        holder.tabText.text = currentBill.billDescription1

        holder.icon1.setOnClickListener { onIcon1Click(currentBill) }
        holder.icon2.setOnClickListener {
            onIcon2Click(currentBill)
            // Update the adapter after removing the item
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return bills.size
    }
}
