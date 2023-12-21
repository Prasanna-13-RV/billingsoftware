package com.example.roomdatabase.data.useCase

import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.repo.BillRepository
import com.example.roomdatabase.domain.useCase.UseCaseGetAllBills
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class UseCaseGetAllBillsTest {
    private lateinit var mockkBillRepository: BillRepository
    private lateinit var mockkUseCaseGetAllBills: UseCaseGetAllBills
    private val bill = BillEntity1(1, "", 1, "", "", "", listOf(BillData1(1, "", "", "", "")))

    @Before
    fun setup() {
        mockkBillRepository = mockk<BillRepository>()
        mockkUseCaseGetAllBills = spyk(UseCaseGetAllBills(mockkBillRepository))
    }

    @Test
    fun `delete by id using mockk`() {
        var response: List<BillEntity1>
        coEvery {
            mockkBillRepository.getAllBills()
        } coAnswers {
            listOf(bill)
        }
        runBlocking {
            response = mockkUseCaseGetAllBills.getAllBills()
        }
        coVerify {
            mockkBillRepository.getAllBills()
        }
        assertNotNull(response)
    }

}