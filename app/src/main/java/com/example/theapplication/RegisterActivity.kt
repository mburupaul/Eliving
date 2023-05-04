package com.example.theapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    var buttonlogin: Button? = null
    var buttonRegister: Button? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        buttonlogin = findViewById(R.id.btnGoToLogin)
        buttonRegister = findViewById(R.id.btnRegister_Reg)

        buttonlogin!!.setOnClickListener {
            val goToLoginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(goToLoginIntent)

        }
        buttonRegister!!.setOnClickListener {

            performsignup()

        }

//        Get email and passwords from the user


    }

    private fun performsignup() {
        val name =findViewById<TextInputEditText>(R.id.edt_name_register)
        val email =findViewById<TextInputEditText>(R.id.edt_email_register)
        val password =findViewById<TextInputEditText>(R.id.edt_password_register)

        val inputName = name.text.toString()
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        if (inputEmail.isEmpty() || inputPassword.isEmpty()){


            Toast.makeText(this,"Kindly Fill All Fields!",Toast.LENGTH_SHORT).show()

            return

        }


        auth.createUserWithEmailAndPassword(inputEmail , inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, lets move to the next Activity
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Registration Successful",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed. Try Again !",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error occurred ${it.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }


}
