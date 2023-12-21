package com.example.roomdatabase.data.entity.bill1

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Test

class BillEntity1Test {

    @Test
    fun `mocking with the data class`() {
        val mockkBillEntity1 = mockk<BillEntity1>()
        every {
            mockkBillEntity1.userId
        } returns "123"
        every {
            mockkBillEntity1.billNo
        } returns 123
        every {
            mockkBillEntity1.billingDate
        } returns "01-08-2020"
        every {
            mockkBillEntity1.fromAddress
        } returns "Chennai"
        every {
            mockkBillEntity1.toAddress
        } returns "Chennai"
        every {
            mockkBillEntity1.billDescription
        } returns listOf()

        val mockBillData1 = spyk(
            BillData1(
                1,
                "Description 1",
                "Description 2",
                "Description 3",
                "Description 4"
            )
        )

        every {
            mockBillData1.billDescriptionId
        } returns 1
        every {
            mockBillData1.billDescription1
        } returns "Description 1"
        every {
            mockBillData1.billDescription2
        } returns "Description 2"
        every {
            mockBillData1.billDescription3
        } returns "Description 3"
        every {
            mockBillData1.billDescription4
        } returns "Description 4"

        BillEntity1(
            1,
            "123",
            123,
            "01-08-2020",
            "Chennai",
            "Chennai",
            listOf(
                BillData1(
                    1,
                    "Description 1",
                    "Description 2",
                    "Description 3",
                    "Description 4"
                )
            )
        )


//
//        val userIdCapture = slot<String>()
//        val billNoCapture = slot<Int>()
//        val billDateCapture = slot<String>()
//        val fromAddressCapture = slot<String>()
//        val toAddressCapture = slot<String>()
//        val billDescriptionCapture = slot<List<BillData1>>()

//        every {
//            BillEntity1(
//                1,
//                capture(userIdCapture),
//                capture(billNoCapture),
//                capture(billDateCapture),
//                capture(fromAddressCapture),
//                capture(toAddressCapture),
//                capture(billDescriptionCapture),
//            )
//        } answers {
//            BillEntity1(
//                1,
//                userIdCapture.captured,
//                billNoCapture.captured,
//                billDateCapture.captured,
//                fromAddressCapture.captured,
//                toAddressCapture.captured,
//                billDescriptionCapture.captured,
//            )
//        }
//
//        val bill = BillEntity1(
//            1,
//            "123",
//            123,
//            "01-08-2020",
//            "Chennai",
//            "Chennai",
//            listOf(
//                BillData1(
//                    1,
//                    "Description 1",
//                    "Description 2",
//                    "Description 3",
//                    "Description 4"
//                )
//            )
//        )
//
//
//
//        assertEquals(
//            bill, BillEntity1(
//                1,
//                userIdCapture.captured,
//                billNoCapture.captured,
//                billDateCapture.captured,
//                fromAddressCapture.captured,
//                toAddressCapture.captured,
//                billDescriptionCapture.captured,
//            )
//        )


//        val mockBillEntity1Creator = mockk<Parcelable.Creator<BillEntity1>>()
//        every { mockBillEntity1Creator.createFromParcel(any()) } returns mockkBillEntity1
//        every { mockBillEntity1Creator.newArray(any()) } returns arrayOfNulls(1)
//
//        mockkStatic(BillEntity1::class)
//        every { BillEntity1.CREATOR } returns mockBillEntity1Creator as BillEntity1.CREATOR
    }
}