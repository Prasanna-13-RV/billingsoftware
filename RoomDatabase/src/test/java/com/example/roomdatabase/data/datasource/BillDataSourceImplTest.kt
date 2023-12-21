package com.example.roomdatabase.data.datasource

import com.example.roomdatabase.data.dao.BillDao
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BillDataSourceImplTest {

    private lateinit var mockkDao: BillDao
    private lateinit var mockkDataSourceImpl: BillDataSourceImpl

    @Before
    fun setup() {
        mockkDao = mockk()
        mockkDataSourceImpl = spyk(BillDataSourceImpl(mockkDao))
    }

    @Test
    fun `data source for get bills with mock`() {

        coEvery {
            mockkDao.getAllBills()
        } returns listOf<BillEntity1>()

        runBlocking {
            val response = mockkDataSourceImpl.getAllBills()
            assertNotNull(response)
        }

        coVerify {
            mockkDao.getAllBills()
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
            mockkDao.insertBill(any())
        } returns 1L

        runBlocking {
            val response = mockkDataSourceImpl.insertBill(bill)
            assertEquals(1L, response)
        }

        coVerify {
            mockkDao.insertBill(match { it == bill })
        }
    }

    @Test
    fun `data source for deleting bills with billNo more than 1 with mock`() {
        coEvery {
            mockkDao.deleteBillById(any())
        } answers {
            1
        }

        runBlocking {
            val resultSuccess = mockkDataSourceImpl.deleteBillById(123)
            assertEquals(1, resultSuccess)
        }

        coVerify {
            mockkDao.deleteBillById(match { it == 123 })
        }
    }

    @Test
    fun `data source for not to delete bill with billNo 0 with mock`() {
        val mockkDao = mockk<BillDao>()
        val mockkDataSourceImpl = BillDataSourceImpl(mockkDao)

        coEvery {
            mockkDao.deleteBillById(any())
        } answers {
            0
        }
        runBlocking {
            val resultSuccess = mockkDataSourceImpl.deleteBillById(0)
            assertEquals(0, resultSuccess)
        }
    }
}