package com.example.billgenerator.ui

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


class MainActivityTest