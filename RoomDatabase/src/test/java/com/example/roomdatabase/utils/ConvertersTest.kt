package com.example.roomdatabase.utils

import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class ConvertersTest {
    private val bill = BillEntity1(
        1,
        "user1",
        1,
        "01-01-2023",
        "Chennai",
        "Chennai",
        listOf(BillData1(1, "Apple", "2", "10", "20"))
    )
    private lateinit var mockkConverters: Converters

    @Before
    fun setup() {
        mockkConverters = mockk<Converters>()
    }

    @Test
    fun `conversion of json to string`() {
        every {
            mockkConverters.listToString(listOf())
        } answers {
            ""
        }
        every {
            mockkConverters.fromString(bill.billDescription.toString())
        } answers {
            bill.billDescription
        }
    }

    @Test
    fun `conversion of string to json`() {
        every {
            mockkConverters.fromString(bill.billDescription.toString())
        } answers {
            bill.billDescription
        }
    }

}