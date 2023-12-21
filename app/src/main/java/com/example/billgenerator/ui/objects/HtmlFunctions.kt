package com.example.billgenerator.ui.objects

import com.example.roomdatabase.data.entity.bill1.BillEntity1

object HtmlFunctions {

    fun calculateTotalAmount(billEntity: BillEntity1): Int {
        var totalAmount = 0

        for (billData in billEntity.billDescription) {
            // Assuming that the 'amount' field is a String representation of an integer
            totalAmount += billData.billDescription4.toIntOrNull() ?: 0
        }
        return totalAmount
    }

    fun addressFormat(address: String): String {
        // Split the address by commas
        val parts = address.split(",")

        // Use buildString to concatenate parts with new lines
        return buildString {
            for (part in parts) {
                append(part.trim()) // Trim to remove leading/trailing whitespaces
                append("<br>") // Add a newline character between parts
            }
        }
    }
}