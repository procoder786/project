package com.amanaryan.firebase_test

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailed_item_view.*

import kotlinx.android.synthetic.main.viewholder.view.*

class DetailedItemView : AppCompatActivity() {
    var TutorMail=""
    var TutorName=""

    var StudentName=""
    var StudentEmail=""
    var StudentMessage=""
    var internetMsg="null"
    var studentPhone=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_item_view)
checkInternet()
        val name=intent.getStringExtra("metaData_name")
        Detail_name.text=name

        val imagee=intent.getStringExtra("metaData_image")
        val gender=intent.getStringExtra("metaData_gender")

val lolipop=intent.getStringExtra("metaData_lolipop")

        if(lolipop=="indoor"){indoorhome.text="He will take your classes in your home."}
        if(lolipop=="outdoor"){indoorhome.text="He will take your classes in his own home."}

        TutorName=intent.getStringExtra("Tutor_name")
        StudentName=intent.getStringExtra("Student_name")
        StudentEmail=intent.getStringExtra("Student_email")
//send button click hone pr internet phle check hoga checkInternet function usekbaaad data upload hoga



        sentStudent_message.visibility=View.GONE

        student_message.setOnClickListener {
            sentStudent_message.visibility=View.GONE
            Toast.makeText(this,"Warning  :Do not use any offensive or abusive language, or send any unwanted messages",Toast.LENGTH_LONG).show()
        }

        if(imagee=="null") {
            if (gender == "MALE") {
                Detailed_BOYImage.visibility= View.VISIBLE
                Detailed_GirlImage.visibility= View.GONE
            }
            if (gender == "FEMALE") {
                Detailed_BOYImage.visibility= View.GONE
                Detailed_GirlImage.visibility= View.VISIBLE
            }
        }else{
            Detailed_BOYImage.visibility= View.GONE
            Detailed_GirlImage.visibility= View.GONE

            Picasso.get().load(imagee).into(Detailed_Image)
        }






        val mobile=intent.getStringExtra("metaData_mobile")

        val qualification=intent.getStringExtra("metaData_qualification")
        Detail_qualification.text=qualification

        val address=intent.getStringExtra("metaData_address")
        Detail_address.text=address
        val description=intent.getStringExtra("metaData_description")
        Detail_description.text=description
        val experience=intent.getStringExtra("metaData_experience")
        Detail_experience.text=experience
        val place=intent.getStringExtra("metaData_place")

        val clasupto=intent.getStringExtra("metaData_clasupto")
        Detail_classs.text=clasupto
        val subject=intent.getStringExtra("metaData_subject")
        Detail_subject.text=subject
        val nearstlocation=intent.getStringExtra("metaData_nearestloc")
        Detail_location.text=nearstlocation
        val age=intent.getStringExtra("metaData_age")
        if(age=="0"||age==""){Detail_age.visibility=View.GONE}else{Detail_age.text=age}
var fee=intent.getStringExtra("metaData_fee")
        if(fee=="NOT AVAILABLE"){Detail_fee.text=fee}else{Detail_fee.text=fee
        Detail_feefee.text=fee}


        Detail_callButton.setOnClickListener{
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(mobile)))
    startActivity(intent)
}







        //message
//        TutorMail=intent.getStringExtra("metaData_Tutor_mailId")
//         TutorName=intent.getStringExtra("metaData_Tutor_mailName")


//        send_student_message.setOnClickListener {
//
//            studentPhoneLayout.visibility=View.GONE
//            if(student_message!=null){
//                studentPhone=studentphoneNumber.text.toString()
//                studentPhoneLayout.visibility=View.VISIBLE
//                if(studentPhone!="null"){
//               internetMsg="ok"
//                checkInternet()
//
//                }else{Toast.makeText(this,"Enter Phone Number",Toast.LENGTH_SHORT).show()}
//            }
//            else{
//                Toast.makeText(this,"Enter the message first",Toast.LENGTH_SHORT).show()
//            }
//        }




        send_student_message.setOnClickListener {
            StudentMessage=student_message.text.toString()

            studentPhone=studentphoneNumber.text.toString()



            if(StudentMessage==""||studentPhone=="")
            {
                Toast.makeText(this,"Enter the message And phone no. Both",Toast.LENGTH_SHORT).show()
            }else{
                    internetMsg="ok"
                    checkInternet()
                }







        }





    }


    private fun checkInternet() {
        val conn=baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=conn.activeNetworkInfo
        if(networkInfo!=null && networkInfo.isConnected){
if (internetMsg=="ok"){sendMessage()}
        }else
        {
            Toast.makeText(this,"Connect Internet",Toast.LENGTH_SHORT).show()
        }
    }


    fun sendMessage(){

            val ref= FirebaseDatabase.getInstance().getReference("MAIN/MESSAGES/TUTORS/${TutorName}/MESSAGES")
            val value=SaveMessage(StudentName,studentPhone,StudentEmail,StudentMessage)

        ref.child(StudentName).setValue (value).addOnSuccessListener {
            sentStudent_message.visibility=View.VISIBLE


        }

    }
}