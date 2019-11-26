package com.amanaryan.firebase_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_messages.*

class Messages : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

       MessageDetails_name.text= intent.getStringExtra("metaData_MessageStudent_Name")

        MessageDetails_email.text=  intent.getStringExtra("metaData_MessageStudent_Email")


        MessageDetails_phone.text=  intent.getStringExtra("metaData_MessageStudent_phone")

        MessageDetailsss.text=  intent.getStringExtra("metaData_MessageStudent_Message")


    }
}
