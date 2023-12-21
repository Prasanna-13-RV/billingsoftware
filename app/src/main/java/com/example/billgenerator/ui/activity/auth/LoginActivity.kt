package com.example.billgenerator.ui.activity.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.billgenerator.databinding.ActivityLoginBinding
import com.example.billgenerator.ui.activity.ShowBillActivity
import com.example.roomdatabase.viewmodel.BillViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var myViewModel : BillViewModel

    //    private lateinit var aidl : AIDL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        binding.viewModel = myViewModel
        binding.lifecycleOwner = this

        binding.signInButtonLogin.setOnClickListener {
            showAlertDialog(this, "Are you sure you want to login?")
        }

        binding.registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showAlertDialog(
        context: Context, title: String
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)

        alertDialogBuilder.setPositiveButton("Login") { _, _ ->
            loginEmail()
        }

        // Negative button (Cancel)
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun loginEmail() {
        val email = binding.emailInputLogin.text.toString()
        val pass = binding.passwordInputLogin.text.toString()

        println("$email $pass credentials")

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ShowBillActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Email or Password is not matched", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }
}