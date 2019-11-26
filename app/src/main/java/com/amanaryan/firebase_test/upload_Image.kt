package com.amanaryan.firebase_test

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_upload__image.*

class upload_Image : AppCompatActivity() {
    var photouri: Uri?=null
    var IMAGEE="null"
    var username=""
    var useremail=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload__image)
        username= intent.getStringExtra("user_name")
       useremail= intent.getStringExtra("user_email")

        profile_uploading.visibility= View.GONE

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        skip.setOnClickListener {
            val i=Intent(this,TutorForm::class.java)
            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_userPicUri", IMAGEE)

            startActivity(i)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){

            photouri = data.data


            profile_uploading.visibility= View.VISIBLE



            UploadImage()
//            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,link)

//            val drable = BitmapDrawable(bitmap)

//            imageView.setBackgroundDrawable(drable)

        }}




        fun UploadImage(){

        val refffff = FirebaseStorage.getInstance().getReference("MAIN/TUTOR/INDOOR/SUBJECT/TutorImage")

        refffff.putFile(photouri!!)

            .addOnSuccessListener {

                refffff.downloadUrl.addOnSuccessListener {

                    IMAGEE= it.toString()

                    Log.d("Save","upload me aagya h ")



                }



                val i=Intent(this,TutorForm::class.java)
                i.putExtra("user_name",username)
                i.putExtra("user_email",useremail)
                i.putExtra("user_userPicUri", IMAGEE)

                startActivity(i)

            }

            .addOnFailureListener {


            }

    }

}
