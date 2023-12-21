package com.example.billgenerator.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.viewmodel.BillViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class ShowBillActivityTest {
//    private val bill = BillEntity1(
//        1,
//        "123",
//        123,
//        "01-08-2020",
//        "Chennai",
//        "Chennai",
//        listOf(
//            BillData1(
//                1,
//                "Description 1",
//                "Description 2",
//                "Description 3",
//                "Description 4"
//            )
//        )
//    )
//
//    private lateinit var activity: Activity
//
//
//
//    @Before
//    fun setup() {
//        activity = mockk<Activity>()
//
//        every { activity.runOnUiThread(any()) } returns Unit
//    }
//
//    @Test
//    fun checkIntent() {
//        val context = mockk<Context>(relaxed = true)
//        val intent = mockk<Intent>(relaxed = true)
//
//        activity.runOnUiThread {
//
//            every { intent.putExtra(any<String>(), any<String>()) } returns intent
//            every { context.startActivity(intent) } returns Unit
//
//            val showBillActivity = ShowBillActivity()
//
//            showBillActivity.viewRowBill(bill)
//
//            verify { intent.putExtra("billEntity", bill) }
//            verify { context.startActivity(intent) }
//        }
//    }
//
//    @Test
//    fun viewRowBillTest() {
//
//        val nameSlot = slot<String>()
//
//        val myIntent = mockk<Intent>(relaxed = true)
//
//        activity.runOnUiThread {
//            val myContext = mockk<Context>()
//            val mainActivity = spyk(ShowBillActivity(myContext))
//
//            every { mainActivity.binding } returns mockk(relaxed = true)
//
//            every { myIntent.putExtra(capture(nameSlot), any<BillEntity1>()) } returns myIntent
//            mainActivity.viewRowBill(bill)
//            verify { myIntent.putExtra("billEntity", bill) }
//            verify { mainActivity.startActivity(any()) }
//
//            assertEquals("billEntit", nameSlot.captured)
//
//            assertEquals(
//                BillEntity1(
//                    1,
//                    "123",
//                    123,
//                    "01-08-2020",
//                    "Chennai",
//                    "Chennai",
//                    listOf(
//                        BillData1(
//                            1,
//                            "Description 1",
//                            "Description 2",
//                            "Description 3",
//                            "Description 4"
//                        )
//                    )
//                ), bill
//            )
//        }
//    }
//
//    @Test
//    fun viewRowBillBundleTest() {
//        activity.runOnUiThread {
//            val myContext = mockk<Context>()
//            val mainActivity = spyk(ShowBillActivity(myContext))
//            every { mainActivity.binding } returns mockk(relaxed = true)
//            val myIntent = mockk<Intent>(relaxed = true)
//            val myBundle = mockk<Bundle>(relaxed = true)
//
//            every {
//                myBundle.putParcelable(any(), any<BillEntity1>())
//                myIntent.putExtra(any(), myBundle)
//            } returns myIntent
//
//            mainActivity.viewRowBillBundle(bill)
//
//            verify {
//                myBundle.putParcelable("myBill", bill)
//                myIntent.putExtra("bills", myBundle)
//            }
//
//            verify { mainActivity.startActivity(any()) }
//
//            assertEquals(
//                BillEntity1(
//                    1,
//                    "123",
//                    123,
//                    "01-08-2020",
//                    "Chennai",
//                    "Chennai",
//                    listOf(
//                        BillData1(
//                            1,
//                            "Description 1",
//                            "Description 2",
//                            "Description 3",
//                            "Description 4"
//                        )
//                    )
//                ), bill
//            )
//        }
//    }
//
//    @Test
//    fun editRowBillTest() {
//        activity.runOnUiThread {
//            val myContext = mockk<Context>()
//            val mainActivity = spyk(ShowBillActivity(myContext))
//            every { mainActivity.binding } returns mockk(relaxed = true)
//            val myIntent = mockk<Intent>(relaxed = true)
//
//            every { myIntent.putExtra(any(), any<BillEntity1>()) } returns myIntent
//
//            mainActivity.editRowBill(myContext, bill)
//
//            verify { myIntent.putExtra("billEntity", bill) }
//
//            verify { mainActivity.startActivity(any()) }
//
//            assertEquals(
//                BillEntity1(
//                    1,
//                    "123",
//                    123,
//                    "01-08-2020",
//                    "Chennai",
//                    "Chennai",
//                    listOf(
//                        BillData1(
//                            1,
//                            "Description 1",
//                            "Description 2",
//                            "Description 3",
//                            "Description 4"
//                        )
//                    )
//                ), bill
//            )
//        }
//    }

}