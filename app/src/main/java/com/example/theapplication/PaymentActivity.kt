package com.example.theapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth


class PaymentActivity : AppCompatActivity() {
    lateinit var Mpesa: TextView
    lateinit var Phone: TextView
    lateinit var Order: TextView

    //FireBase
    private  lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        supportActionBar?.hide()

        Mpesa=findViewById(R.id.TvPay)
        Phone=findViewById(R.id.TvCall)
        Order=findViewById(R.id.TvOrder)

        //set to access MPESa
        Order.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","sasa.sales@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));

        }


            Mpesa.setOnClickListener{
            val simToolKitLaunchIntent=applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }


        //set for calls
        Phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254713207935"))
            if (ContextCompat.checkSelfPermission(
                    this@PaymentActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
//                  request
                ActivityCompat.requestPermissions(
                    this@PaymentActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }
        }
        }

    }
