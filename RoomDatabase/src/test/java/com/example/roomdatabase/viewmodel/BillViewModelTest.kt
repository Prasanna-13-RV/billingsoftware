package com.example.roomdatabase.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.repo.FakeRepository
import com.example.roomdatabase.domain.useCase.UseCaseDeleteBillById
import com.example.roomdatabase.domain.useCase.UseCaseGetAllBills
import com.example.roomdatabase.domain.useCase.UseCaseInsertBills
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class BillViewModelTest {

//    Testing using Fake Repository junit

    private lateinit var viewModel: BillViewModel
    private lateinit var useCaseDeleteBillById: UseCaseDeleteBillById
    private lateinit var useCaseInsertBills: UseCaseInsertBills
    private lateinit var useCaseGetAllBills: UseCaseGetAllBills
    private val fakeRepo = FakeRepository()

    private val bill =
        BillEntity1(
            1,
            "",
            1,
            "01-08-2023",
            "Chennai",
            "Chennai",
            listOf(
                BillData1(
                    1,
                    "Apple",
                    "2",
                    "10",
                    "20"
                )
            )
        )

    @get:Rule
    val instanceTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        useCaseDeleteBillById = UseCaseDeleteBillById(fakeRepo)
        useCaseInsertBills = UseCaseInsertBills(fakeRepo)
        useCaseGetAllBills = UseCaseGetAllBills(fakeRepo)
        viewModel = BillViewModel(useCaseGetAllBills, useCaseInsertBills, useCaseDeleteBillById)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `junit testing for the get bills of LiveData in viewModel`() = runBlocking {

        val myBills = fakeRepo.getAllBills()
        viewModel.getBills()
        assertEquals(myBills, viewModel.bills.value)
    }

    @Test
    fun `junit testing for the insert bills in viewModel`() = runBlocking {
        viewModel.insertBills(bill)
        viewModel.getBills()
        val insertedBill = viewModel.bills.value?.last()
        assertEquals(bill, insertedBill)
    }



    @Test
    fun `junit testing for deleting the bill with id`() = runBlocking {
        viewModel.deleteBillById(1)
        viewModel.getBills()
//        var answer: Boolean = false
//        val iterator = viewModel.bills.value?.iterator()
//        if (iterator != null) {
//            while (iterator.hasNext()) {
//                val bill = iterator.next()
//                if (bill.billId) {
//                    answer = true
//                }
//            }
//        }
//        assertEquals(false, answer)

        val iterator: List<BillEntity1>? = viewModel.bills.value?.filter {
            it.billId == 1
        }
        assertThat(iterator).isEmpty()
    }


//    Mockk

//    private lateinit var mockkViewModel: BillViewModel
//    private lateinit var mockkUseCaseDeleteBillById: UseCaseDeleteBillById
//    private lateinit var mockkUseCaseInsertBills: UseCaseInsertBills
//    private lateinit var mockkUseCaseGetAllBills: UseCaseGetAllBills
//
//    private val bill = BillEntity1(1, "", 1, "", "", "", listOf(BillData1(1, "", "", "", "")))
//
//    @get:Rule
//    val instanceTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Before
//    fun setup() {
//        Dispatchers.setMain(Dispatchers.Unconfined)
//
//        mockkUseCaseGetAllBills = mockk<UseCaseGetAllBills>()
//        mockkUseCaseInsertBills = mockk<UseCaseInsertBills>()
//        mockkUseCaseDeleteBillById = mockk<UseCaseDeleteBillById>()
//        mockkViewModel = spyk(
//            BillViewModel(
//                mockkUseCaseGetAllBills,
//                mockkUseCaseInsertBills,
//                mockkUseCaseDeleteBillById
//            )
//        )
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        clearAllMocks()
//    }
//
//
//    @Test
//    fun `get bills mockk test`() = runBlocking {
//
//        val expectedBills = listOf(bill)
//        coEvery { mockkUseCaseGetAllBills.getAllBills() } returns expectedBills
//
//        mockkViewModel.getBills()
//
//        runBlocking {
//            val actualBills = mockkUseCaseGetAllBills.getAllBills()
//            assertNotNull(actualBills)
//            assertEquals(expectedBills, actualBills)
//        }
//
//        verify { runBlocking { mockkUseCaseGetAllBills.getAllBills() } }
//    }
//
//
//    @Test
//    fun `insert bills mockk`() = runBlocking {
//
//        val billCapture = slot<BillEntity1>()
//
//        coEvery {
//            mockkUseCaseInsertBills.insertBill(any())
//        } coAnswers {
//            1L
//        }
//        mockkViewModel.insertBills(bill)
//
//
//        coVerify {
////            mockkRepository.insertBill(match { it == bill })
//            mockkUseCaseInsertBills.insertBill(capture(billCapture))
//        }
//        val captureBill = billCapture.captured
//        assertEquals(bill, captureBill)
//    }
//
//    @Test
//    fun `delete Bills By id`() = runBlocking {
//        coEvery {
//            mockkUseCaseDeleteBillById.deleteBillById(any())
//        } coAnswers {
//            1
//        }
//        val idCapture = slot<Int>()
//        runBlocking {
//            mockkViewModel.deleteBillById(123)
//        }
//        coVerify {
//            mockkUseCaseDeleteBillById.deleteBillById(capture(idCapture))
//        }
//        val captureId = idCapture.captured
//        assertEquals(captureId, 123)
//    }
}