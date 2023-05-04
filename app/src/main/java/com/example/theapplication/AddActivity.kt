package com.example.theapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class AddActivity : AppCompatActivity() {

    var eidtTextPName: EditText? = null
    var eidtTextPhoneNumber: EditText? = null
    var eidtTextLocation: EditText? = null
    var eidtTextPrice: EditText? = null


    var buttonUpload: Button? = null
    var imageView: ImageView? = null
    private var mImageUri: Uri? = null

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        eidtTextPName = findViewById(R.id.mEdtPName)
        eidtTextPhoneNumber = findViewById(R.id.mEdtPhoneNumber)
        eidtTextLocation = findViewById(R.id.mEdtLocation)
        eidtTextPrice = findViewById(R.id.mEdtPrice)
        buttonUpload = findViewById(R.id.mBtnSave_Add)
        imageView = findViewById(R.id.img_save)



                imageView!!.setOnClickListener {
                    pickImageGallery()
                }


                buttonUpload!!.setOnClickListener {
                    val productName = eidtTextPName!!.text.toString().trim()
                    val phoneNumber = eidtTextPhoneNumber!!.text.toString().trim()
                    val productLocation = eidtTextLocation!!.text.toString().trim()
                    val productPrice = eidtTextPrice!!.text.toString().trim()
                    if (productName.isEmpty()){
                        eidtTextPName!!.setError("Please fill this Field!!")
                        eidtTextPName!!.requestFocus()
                    }else if (phoneNumber.isEmpty()){
                        eidtTextPhoneNumber!!.setError("Please fill this Field!!")
                        eidtTextPhoneNumber!!.requestFocus()
                    }else if (productLocation.isEmpty()){
                        eidtTextLocation!!.setError("Please fill this Field!!")
                        eidtTextLocation!!.requestFocus()
                    }else if (productPrice.isEmpty()){
                        eidtTextPrice!!.setError("Please fill this Field!!")
                        eidtTextPrice!!.requestFocus()
                    }else {
                        FireBaseStorageManager().uploadImage(this, mImageUri!!,productName,phoneNumber,productPrice,
                            productLocation,System.currentTimeMillis().toString())

                    }
                }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE &&  resultCode == RESULT_OK){
            imageView?.setImageURI(data?.data)
            mImageUri = data?.data
        }

    }

}