package com.example.roomdatabase.data.useCase

import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.repo.BillRepository
import com.example.roomdatabase.domain.useCase.UseCaseInsertBills
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class UseCaseInsertBillsTest {
    private lateinit var mockkBillRepository: BillRepository
    private lateinit var mockkUseCaseInsertBills: UseCaseInsertBills
    private val bill = BillEntity1(1, "", 1, "", "", "", listOf(BillData1(1, "", "", "", "")))

    @Before
    fun setup() {
        mockkBillRepository = mockk<BillRepository>()
        mockkUseCaseInsertBills = spyk(UseCaseInsertBills(mockkBillRepository))
    }

    @Test
    fun `delete by id using mockk`() {
        var response: Long
        val captureEntity = slot<BillEntity1>()
        coEvery {
            mockkBillRepository.insertBill(bill)
        } coAnswers {
            1L
        }

        runBlocking {
            response = mockkUseCaseInsertBills.insertBill(bill)
        }

        coVerify {
            mockkBillRepository.insertBill(capture(captureEntity))
        }

        assertEquals(bill, captureEntity.captured)
        assertEquals(1L, response)

    }

}