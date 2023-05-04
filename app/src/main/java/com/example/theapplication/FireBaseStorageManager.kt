package com.example.theapplication

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*



class FireBaseStorageManager{

    private val mStorageRef = FirebaseStorage.getInstance().reference
    private lateinit var mProgressDialog: ProgressDialog

    fun uploadImage(context: Context,imageFileUri: Uri,productname: String, phoneNumber: String, productprice: String, productlocation: String, id: String){
        mProgressDialog = ProgressDialog(context)
        mProgressDialog.setMessage("Please wait, Product is being uploaded")
        mProgressDialog.show()
        val date = Date()
        val fileReference = mStorageRef.child("Uploads/$id")

        fileReference.putFile(imageFileUri)
            .continueWithTask(object : Continuation<UploadTask.TaskSnapshot?, Task<Uri?>?> {
                @Throws(Exception::class)
                override fun then(task: Task<UploadTask.TaskSnapshot?>): Task<Uri?>? {
                    if (!task.isSuccessful()) {
                        throw task.getException()!!
                    }

                    // Continue with the task to get the download URL
                    //change made here
                    return fileReference.downloadUrl
                }
            }).addOnCompleteListener(OnCompleteListener<Uri?> { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    var refence = FirebaseDatabase.getInstance().getReference().child("Products/$id")
                    var product = Product(productname,phoneNumber,productprice,productlocation,id, downloadUri.toString())
                    refence.setValue(product).addOnCompleteListener{
                            task->
                        mProgressDialog.dismiss()
                        if (task.isSuccessful){
                            //Toast upload successful
                            context.startActivity(Intent(context,MainActivity::class.java))
                            mProgressDialog.dismiss()

                        }else{

//                            Toast.makeText(this,"Data saved Successfully",
//                                Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })



    }
}




