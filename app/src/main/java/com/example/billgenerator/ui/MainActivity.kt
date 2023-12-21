package com.example.billgenerator.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.billgenerator.databinding.ActivityMainBinding
import com.example.billgenerator.ui.activity.auth.LoginActivity
import com.example.billgenerator.viewmodel.AIDLViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var aidlViewModel: AIDLViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

//        aidlViewModel.responseText.observe(this, Observer { response ->
//            binding.noContentRV.text = response
//        })

        // Call the bindToService method from the ViewModel
//        aidlViewModel.bindToService(this)
    }
    override fun onDestroy() {
        // Call the unbindService method from the ViewModel
        aidlViewModel.unbindService(this)
        super.onDestroy()
    }
}