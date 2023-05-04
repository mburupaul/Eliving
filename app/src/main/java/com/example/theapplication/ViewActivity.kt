package com.example.theapplication

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewActivity : AppCompatActivity() {

 var btnPay:Button ?= null

lateinit var mListProducts:ListView
    lateinit var myadapter : CustomAdapter
    lateinit var products : ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        btnPay = findViewById(R.id.mBtnBuy)

        mListProducts = findViewById(R.id.mListProducts)
        products = ArrayList()
        myadapter = CustomAdapter(applicationContext,products)
//heeeeeey
        var progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        //Access the table in the database

        var my_db = FirebaseDatabase.getInstance().reference.child("Products")
        //Start retrieving data
        progress.show()
        my_db.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                //Get the data and put it on the arraylist users
                products.clear()
                for (snap in p0.children){
                    var product = snap.getValue(Product::class.java)
                    products.add(product!!)
                }
                //Notify the adapter that data has changed
                myadapter.notifyDataSetChanged()
                progress.dismiss()
            }

            override fun onCancelled(p0: DatabaseError) {
                progress.dismiss()
                Toast.makeText(applicationContext,"DB Locked",Toast.LENGTH_LONG).show()
            }
        })

        mListProducts.adapter = myadapter
        //        set on an item click listener to the list view
//        btnPay!!.setOnClickListener {
//            val openpay = Intent(applicationContext,PaymentActivity::class.java)
//            startActivity(openpay)
//        }


            mListProducts.setOnItemClickListener { adapterView, view, i, l ->
                val ProductId = products.get(i).id
                val deletionReference = FirebaseDatabase.getInstance().
                getReference().child("Products/$ProductId")
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
                        Toast.makeText(applicationContext,"Deleted Successfully",
                            Toast.LENGTH_LONG).show()
                    })

            }





        }
    }
