package com.amanaryan.firebase_test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_tutor_form.*

class TutorForm : AppCompatActivity() {
    var photouri: Uri?=null
    var IMAGEE="null"
    var PLACE="I can go to students place"
    var CLASupto="PREP TO STD 4"
    var SUBJECTT="ALL"
    var NEARloc="RATUROAD"
    var GENDER="MALE"
    var username=""
    var useremail=""
    var userimg=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_form)







        IMAGEE=intent.getStringExtra("user_userPicUri")
        username= intent.getStringExtra("user_name")
        useremail= intent.getStringExtra("user_email")

//        userimg= intent.getStringExtra("user_photoUrl")

        tutor_progressLayout.visibility= View.GONE
        var chkName=""
        var chkMobile=""

        uploaddata.setOnClickListener {

            chkName=form_name.text.toString()
            chkMobile=form_MOBILE.text.toString()

            if(chkName==""||chkMobile==""){Toast.makeText(this,"Enter the valid Name and Mobile no.",Toast.LENGTH_SHORT).show()}
            else{
                checkInternet()
            }
        }




            val gen=arrayOf("MALE","FEMALE")
            val g=findViewById<Spinner>(R.id.form_GENDER)
            if(g!=null){
                val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,gen)
                form_GENDER.adapter=arrayAdapter
                g.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        GENDER=gen[p2]
                    }
                }

            }

            //indoor outdoor place spinner

            val plc=arrayOf("I can go to students place","Students have to came on my place"," Both ")
            val plce=findViewById<Spinner>(R.id.form_placeOfTeaching)
            if(plce!=null){
                val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,plc)
                form_placeOfTeaching.adapter=arrayAdapter
                plce.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        PLACE=plc[p2]
                    }
                }

            }



            //classes upto tutor can taught


            val cls=arrayOf("PREP TO STD 4","STD 5 TO STD 8","STD 9 TO STD 10","STD 11 TO STD 12")
            val clas=findViewById<Spinner>(R.id.form_classes_upto_taught)
            if(clas!=null){
                val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,cls)
                form_classes_upto_taught.adapter=arrayAdapter
                clas.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        CLASupto=cls[p2]
                        checkclass()
                    }
                }

            }

            //specified subject

            form_subjectLayout.visibility=View.GONE


            val sub=arrayOf("ALL","PCM","PCB","ENGLISH","ACCOUNTS")
            val subj=findViewById<Spinner>(R.id.form_specifiedsubject)
            if(subj!=null){
                val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,sub)
                form_specifiedsubject.adapter=arrayAdapter
                subj.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        SUBJECTT=sub[p2]
                    }
                }

            }


            //form neaest location

            form_nearestLocation

            val nrloc=arrayOf("RATUROAD","LALPUR","DHURWA","MAINROAD","DORANDA","ITI","MORABADI")
            val nrlocation=findViewById<Spinner>(R.id.form_nearestLocation)
            if(nrlocation!=null){
                val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,nrloc)
                form_nearestLocation.adapter=arrayAdapter
                nrlocation.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        NEARloc=nrloc[p2]
                    }
                }

            }








        }



//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
//
//            photouri = data.data
//
//
//            profile_uploading.visibility= View.VISIBLE
//
//            profile_not_uploaded.visibility=View.GONE
//            UploadImage()
//
//
//
//
//        }
//
//
//
//
//    }



    var placeCount=0
    fun uploadData()
    {
        //indoor outdoor check
        if(PLACE=="I can go to students place"){PLACE="INDOOR"}
        if(PLACE=="Students have to came on my place"){PLACE="OUTDOOR"}
        if(PLACE==" Both "){placeCount=1}

        if (placeCount==1){PLACE="INDOOR"
            Toast.makeText(this," indoor gya",Toast.LENGTH_SHORT).show()
        }

        if (placeCount==2){PLACE="OUTDOOR"
            Toast.makeText(this," outdoor gya",Toast.LENGTH_SHORT).show()
        }



        //upload data

        val NAME=form_name.text.toString()
        val MOBILE=form_MOBILE.text.toString()
        val AADHAR=form_AADHAR.text.toString()

        var  QUALIFICATION=form_QUALIFICATION.text.toString()
        if(QUALIFICATION==""){QUALIFICATION="NOT AVAILABLE"}

        var AGE=form_AGE.text.toString()
        if(AGE==""){AGE="NOT AVAILABLE"}

        val EMAIL=form_EMAILID.text.toString()
        var ADDRESSS=form_ADDRESS.text.toString()
        if(ADDRESSS==""){ADDRESSS="NOT AVAILABLE"}
        var FEE=form_fee.text.toString()
        if(FEE==""){FEE="NOT AVAILABLE"}
        var DESCRIPTION=form_DESCRIPTION.text.toString()
        if(DESCRIPTION==""){DESCRIPTION="NOT AVAILABLE"}
        var EXPERIENCE=form_EXPERIENCE.text.toString()
        if(EXPERIENCE==""){EXPERIENCE="NOT AVAILABLE"}


        val ref= FirebaseDatabase.getInstance().getReference("MAIN/TUTOR/${PLACE}/${CLASupto}/${SUBJECTT}/${NEARloc}")
        val value=SaveData(NAME,IMAGEE,MOBILE,AADHAR,QUALIFICATION,
            AGE,GENDER,EMAIL,ADDRESSS,FEE,DESCRIPTION,EXPERIENCE,PLACE,CLASupto,SUBJECTT,NEARloc,username,useremail)
        ref.child(NAME).setValue (value).addOnSuccessListener {
            Toast.makeText(this,"submited",Toast.LENGTH_SHORT).show()
            check() }
            .addOnFailureListener{
                Toast.makeText(this," Not submited",Toast.LENGTH_SHORT).show()

            }
    }
    fun check(){
        if (placeCount==1){
            placeCount=2
            uploadData()
            Toast.makeText(this," check hogya",Toast.LENGTH_SHORT).show()

        }else {

            val i = Intent(this,login::class.java)

            startActivity(i)
        }
    }
    fun checkclass(){
        if(CLASupto=="STD 9 TO STD 10"||CLASupto=="STD 11 TO STD 12") {
            form_subjectLayout.visibility=View.VISIBLE
        }else{
            form_subjectLayout.visibility=View.GONE
        }

    }


    private fun checkInternet() {
        val conn=baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=conn.activeNetworkInfo
        if(networkInfo!=null && networkInfo.isConnected){
            tutor_progressLayout.visibility= View.VISIBLE
            uploadData()
        }else
        {
            Toast.makeText(this,"Connect Internet",Toast.LENGTH_SHORT).show()
        }
    }

}
