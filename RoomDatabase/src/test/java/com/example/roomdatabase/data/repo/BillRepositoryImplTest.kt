package com.example.roomdatabase.data.repo

import com.example.roomdatabase.data.datasource.inter.BillDataSource
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class BillRepositoryImplTest {
    private lateinit var mockkDataSource: BillDataSource
    private lateinit var mockkBillRepositoryImpl: BillRepositoryImpl

    @Before
    fun setup() {
        mockkDataSource = mockk<BillDataSource>()
        mockkBillRepositoryImpl = BillRepositoryImpl(mockkDataSource)
    }

    @Test
    fun `data source for get bills with mock`() {

        coEvery {
            mockkDataSource.getAllBills()
        } returns listOf<BillEntity1>()

        coVerify {
            mockkDataSource.getAllBills()
        }

        runBlocking {
            val response = mockkBillRepositoryImpl.getAllBills()
            assertNotNull(response)
        }

    }

    @Test
    fun `data source for insert bills with mock`() {

        val bill = BillEntity1(
            1,
            "user1",
            1,
            "01-01-2023",
            "Chennai",
            "Chennai",
            listOf(BillData1(1, "Apple", "2", "10", "20"))
        )

        coEvery {
            mockkDataSource.insertBill(any())
        } returns 1L

        coVerify {
            mockkDataSource.insertBill(match { it == bill })
        }
        runBlocking {
            val response = mockkBillRepositoryImpl.insertBill(bill)
            assertEquals(1L, response)
        }

    }

    @Test
    fun `data source for deleting bills with billNo more than 1 with mock`() {
        coEvery {
            mockkDataSource.deleteBillById(any())
        } answers {
            1
        }

        runBlocking {
            val resultSuccess = mockkBillRepositoryImpl.deleteBillById(123)
            assertEquals(1, resultSuccess)
        }

        coVerify {
            mockkDataSource.deleteBillById(match { it == 123 })
        }
    }

    @Test
    fun `data source for not to delete bill with billNo 0 with mock`() {
        coEvery {
            mockkDataSource.deleteBillById(any())
        } answers {
            0
        }
        runBlocking {
            val resultSuccess = mockkBillRepositoryImpl.deleteBillById(0)
            assertEquals(0, resultSuccess)
        }
    }
}