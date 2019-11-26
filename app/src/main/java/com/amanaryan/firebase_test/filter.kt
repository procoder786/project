package com.amanaryan.firebase_test

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_filter.*

class filter : AppCompatActivity() {
    var STD="PREP TO STD 4"
    var subject="ALL"
    var location="RATUROAD"
    var checkLayout=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val username= intent.getStringExtra("user_name")
        val useremail= intent.getStringExtra("user_email")
        val userimg= intent.getStringExtra("user_photoUrl")

checkInternet()



        //layout changess important
        var checkLay=intent.getStringExtra("intentNumber")
        if(checkLay=="1"){
            noFilterClassLayout.visibility=View.VISIBLE
        }else{noFilterClassLayout.visibility=View.GONE
        }
        //layout changess important

        //notice

        filter_noticeClass.visibility=View.GONE
        filter_noticeLocation.visibility=View.GONE

        filter_class_layout.setOnClickListener {
            filter_noticeClass.visibility=View.VISIBLE
            filter_noticeLocation.visibility=View.GONE
        }
        filter_locat_layout.setOnClickListener {
            filter_noticeClass.visibility=View.GONE
            filter_noticeLocation.visibility=View.VISIBLE
        }
        filter_subjectLayout.setOnClickListener {

            filter_noticeClass.visibility=View.GONE
            filter_noticeLocation.visibility=View.GONE

        }
        //location spinner

        val loc=arrayOf("RATUROAD","LALPUR","DHURWA","MAINROAD","DORANDA","ITI","MORABADI")
        val loctn=findViewById<Spinner>(R.id.filter_location)
        if(loctn!=null){
            val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,loc)
            filter_location.adapter=arrayAdapter
            loctn.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    location=loc[p2]
                }
            }

        }



        val cls=arrayOf("PREP TO STD 4","STD 5 TO STD 8","STD 9 TO STD 10","STD 11 TO STD 12")
        val clas=findViewById<Spinner>(R.id.filter_class)
        if(clas!=null){
            val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,cls)
            filter_class.adapter=arrayAdapter
            clas.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    STD=cls[p2]
                    subjectcheck()
                }
            }

        }
        // subject layout visible or not section
        filter_subjectLayout.visibility = View.GONE


        val sub=arrayOf("ALL","PCM","PCB","ENGLISH","ACCOUNTS")
        val subj=findViewById<Spinner>(R.id.filter_subject)
        if(subj!=null){
            val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,sub)
            filter_subject.adapter=arrayAdapter
            subj.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    subject=sub[p2]
                }
            }

        }
        //back to mainActivity
        filter_apply.setOnClickListener {

            var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)


            startActivity(i)
        }














        //class layuot codes start

        class_prep.setOnClickListener {


           var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)




            startActivity(i)

        }

        class_1.setOnClickListener {
            var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }

        class_2.setOnClickListener {
            var  i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }


        class_3.setOnClickListener {
            var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }

        class_4.setOnClickListener {
            var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }

        class_5.setOnClickListener {

            STD="STD 5 TO STD 8"
           var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
        class_6.setOnClickListener {

            STD="STD 5 TO STD 8"
           var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
        class_7.setOnClickListener {

            STD="STD 5 TO STD 8"
             var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
        class_8.setOnClickListener {

            STD="STD 5 TO STD 8"
          var  i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
        class_9.setOnClickListener {

            STD="STD 9 TO STD 10"
            subject="PCM"
           var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
        class_10.setOnClickListener {

            STD="STD 9 TO STD 10"
            subject="PCM"
           var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
            class_11.setOnClickListener {

                subject="PCM"
                STD="STD 11 TO STD 12"
           var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

                i.putExtra("user_name",username)
                i.putExtra("user_email",useremail)
                i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
        class_12.setOnClickListener {

            subject="PCM"
            STD="STD 11 TO STD 12"
             var i= Intent(this,MainActivity::class.java)
            i.putExtra("subject",subject)
            i.putExtra("class",STD)
            i.putExtra("location",location)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }

    }
    fun subjectcheck() {
        filter_subjectLayout.visibility = View.GONE

        if (STD == "STD 9 TO STD 10") {

            filter_subjectLayout.visibility = View.VISIBLE
        }
        if (STD == "STD 11 TO STD 12") {

            filter_subjectLayout.visibility = View.VISIBLE
        }
    }

    private fun checkInternet() {
        val conn=baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=conn.activeNetworkInfo
        if(networkInfo!=null && networkInfo.isConnected){

        }else
        {
         Toast.makeText(this,"Connect Internet",Toast.LENGTH_SHORT).show()
        }
    }

}



