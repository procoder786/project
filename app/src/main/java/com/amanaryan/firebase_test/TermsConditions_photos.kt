package com.amanaryan.firebase_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_terms_conditions_photos.*

class TermsConditions_photos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_conditions_photos)
        ok.setOnClickListener {
            startActivity(Intent(this,login::class.java))
        }
    }
}
