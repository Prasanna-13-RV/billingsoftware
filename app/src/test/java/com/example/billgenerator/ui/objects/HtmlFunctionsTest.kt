package com.example.billgenerator.ui.objects

import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class HtmlFunctionsTest {
    private val bill = BillEntity1(
        1,
        "user1",
        1,
        "01-01-2023",
        "Address1, Address2, Address3",
        "Chennai, Chennai, Chennai",
        listOf(BillData1(1, "Apple", "2", "10", "20"))
    )

    @Before
    fun setup() {
        mockkObject(HtmlFunctions)
    }

    @Test
    fun `calculate total amount using junit`() {

        val result = HtmlFunctions.calculateTotalAmount(bill)

        assertThat(result).isEqualTo(20)

    }

    @Test
    fun `address format using junit`() {

        val fromAddress = HtmlFunctions.addressFormat(bill.fromAddress)
        assertThat(fromAddress).isEqualTo("Address1<br>Address2<br>Address3<br>")

        val toAddress = HtmlFunctions.addressFormat(bill.toAddress)
        assertThat(toAddress).isEqualTo("Chennai<br>Chennai<br>Chennai<br>")
    }
}