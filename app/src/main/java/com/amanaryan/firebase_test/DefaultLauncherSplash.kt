package com.amanaryan.firebase_test

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

import androidx.constraintlayout.widget.ConstraintLayout

class DefaultLauncherSplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_launcher_splash)

checkInternet()


    }

    private fun checkInternet() {
        val conn=baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=conn.activeNetworkInfo
        if(networkInfo!=null && networkInfo.isConnected){
            startActivity(Intent(this,login::class.java))
        }else
        {

            val anim=findViewById<TextView>(R.id.connect_internet_i)
            val animation: Animation = AnimationUtils.loadAnimation(this,R.anim.blink)
            anim.startAnimation(animation)

        Handler().postDelayed({
            startActivity(Intent(this,DefaultLauncherSplash::class.java))
            finish()
        },2000)
        }
        }

}
