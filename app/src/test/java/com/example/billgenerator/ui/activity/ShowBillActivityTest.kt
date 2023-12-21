package com.example.billgenerator.ui.activity

import android.content.Context
import android.content.Intent
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test


class ShowBillActivityTest {

    private val bill = BillEntity1(
        1,
        "user1",
        1,
        "01-01-2023",
        "Address1, Address2, Address3",
        "Chennai, Chennai, Chennai",
        listOf(BillData1(1, "Apple", "2", "10", "20"))
    )

    @Test
    fun `check the Intent key value pair`() {
        val context = mockk<Context>(relaxed = true)
        val intent = mockk<Intent>(relaxed = true)

        every { intent.putExtra(any<String>(), any<String>()) } returns intent
        every { context.startActivity(intent) } returns Unit

        val showBillActivity = ShowBillActivity(context)

        showBillActivity.viewRowBill(bill)

        verify { intent.putExtra("billEntity", bill) }
        verify { context.startActivity(intent) }

    }
}