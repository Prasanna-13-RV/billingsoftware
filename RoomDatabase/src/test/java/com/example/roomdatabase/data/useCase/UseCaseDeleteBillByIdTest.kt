package com.example.roomdatabase.data.useCase

import com.example.roomdatabase.data.repo.BillRepository
import com.example.roomdatabase.domain.useCase.UseCaseDeleteBillById
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class UseCaseDeleteBillByIdTest {

    private lateinit var mockkBillRepository: BillRepository
    private lateinit var mockkUseCaseDeleteBillById: UseCaseDeleteBillById

    @Before
    fun setup() {
        mockkBillRepository = mockk<BillRepository>()
        mockkUseCaseDeleteBillById = spyk(UseCaseDeleteBillById(mockkBillRepository))
    }

    @Test
    fun `delete by id using mockk`() {
        var response: Int
        val captureBillId = slot<Int>()
        coEvery {
            mockkBillRepository.deleteBillById(123)
        } coAnswers {
            1
        }

        runBlocking {
            response = mockkUseCaseDeleteBillById.deleteBillById(123)
        }

        coVerify {
            mockkBillRepository.deleteBillById(capture(captureBillId))
        }

        assertEquals(123, captureBillId.captured)
        assertEquals(1, response)

    }

}