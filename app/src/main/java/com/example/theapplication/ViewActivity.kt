package com.example.theapplication

import android.Manifest
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewActivity : AppCompatActivity() {

    var btnpay: Button ?=null
    lateinit var btncall: Button

    lateinit var mListProducts: ListView
    lateinit var myadapter: CustomAdapter
    lateinit var products: ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        btnpay = findViewById(R.id.mBtnpay)
       // btncall = findViewById(R.id.mBtncall)

        mListProducts = findViewById(R.id.mListProducts)
        products = ArrayList()
        myadapter = CustomAdapter(applicationContext, products)
//heeeeeey
        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        //Access the table in the database

        val my_db = FirebaseDatabase.getInstance().reference.child("Products")
        //Start retrieving data
        progress.show()
        my_db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //Get the data and put it on the arraylist users
                products.clear()
                for (snap in p0.children) {
                    var product = snap.getValue(Product::class.java)
                    products.add(product!!)
                }
                //Notify the adapter that data has changed
                myadapter.notifyDataSetChanged()
                progress.dismiss()
            }


            override fun onCancelled(p0: DatabaseError) {
                progress.dismiss()
                Toast.makeText(applicationContext, "DB Locked", Toast.LENGTH_LONG).show()
            }
        })

        mListProducts.adapter = myadapter
        //set for calls
       // btnpay.setOnClickListener {
         //   val uri: Uri = Uri.parse("smsto:0798701924")

    //        val intent = Intent(Intent.ACTION_SENDTO, uri)

    //        intent.putExtra("Hello", "How are you doing")

    //        startActivity(intent)
    //    }
     //   btncall.setOnClickListener {
     //       val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254713207935"))
    //        if (ContextCompat.checkSelfPermission(
     //               this@ViewActivity,
    //                Manifest.permission.CALL_PHONE
      //          ) != PackageManager.PERMISSION_GRANTED
        //    ) {
//                  request
          //      ActivityCompat.requestPermissions(
            //        this@ViewActivity,
              //      arrayOf(Manifest.permission.CALL_PHONE),
                //    1
                //)
            //} else {
              //  startActivity(intent)
            //}

                //        set on an item click listener to the list view
                //   btnPay!!.setOnClickListener {
                //       val openpay = Intent(applicationContext,PaymentActivity::class.java)
                //     startActivity(openpay)
                //   }


                mListProducts.setOnItemClickListener { _, view, i, l ->
                    val ProductId = products.get(i).id
                    val deletionReference =
                        FirebaseDatabase.getInstance().getReference().child("Products/$ProductId")
//         set an alert when someone clicks on an item
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Alert!!")
                    alertDialog.setMessage("Select an action you want to perform")
                    alertDialog.setNegativeButton("Update",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            //Dismiss
                        })
                    alertDialog.setPositiveButton("Delete",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            deletionReference.removeValue()
                            Toast.makeText(
                                applicationContext, "Deleted Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                        })

                }


            }
       }

