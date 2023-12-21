package com.example.billgenerator.ui.activity.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.billgenerator.R
import com.example.billgenerator.databinding.ActivityRegisterBinding
import com.example.billgenerator.ui.activity.ShowBillActivity
import com.example.roomdatabase.data.entity.user.UserEntity
import com.example.roomdatabase.viewmodel.BillViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var dbRef: DatabaseReference

    @Inject
    lateinit var viewModel: BillViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        supportActionBar?.hide()

        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signUpButtonRegister.setOnClickListener {
            showAlertDialog(this, "Are you you want to create an account?")
//            createUser(db)
        }

        binding.loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

//        binding.signupGoogleRegister.setOnClickListener {
//            signInGoogle()
//        }
    }

    private fun showAlertDialog(
        context: Context, title: String
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)

        alertDialogBuilder.setPositiveButton("Create Account") { _, _ ->
            signInEmail()
        }

        // Negative button (Cancel)
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun signInEmail() {
        val name = binding.nameInputRegister.text.toString()
        val email = binding.emailInputRegister.text.toString()
        val password = binding.passwordInputRegister.text.toString()
        val confirmPass = binding.conPasswordInputRegister.text.toString()

        println("$email $password $confirmPass credentials")

        val userId = dbRef.push().key!!

        if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (password == confirmPass) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }
                    }
            } else {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        try {
            launcher.launch(signInIntent)
        } catch (e: Exception) {
            Log.d("myGoogleService", e.toString())
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            } else {
                Log.d("myGoogleService", "$result , ${Activity.RESULT_OK}")

            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        try {
            auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Authentication success
                    Toast.makeText(this, "Successfully registered", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this, ShowBillActivity::class.java)
                    startActivity(intent)
                } else {
                    // Authentication failure, log the specific error
                    Log.e("myGoogleService", "signInWithCredential: ${task.exception}")
                    Toast.makeText(
                        this,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } catch (e: Exception) {
            Log.d("myGoogleService", e.toString())
        }
    }
}