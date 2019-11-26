package com.amanaryan.firebase_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profolio.*

class profolio : AppCompatActivity() {
var RMessage="null"
    var Rname=""
    var Remail=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profolio)


        Rname= intent.getStringExtra("user_name")
        Remail= intent.getStringExtra("user_email")

        profile_name.text=Rname
        profile_email.text= Remail
        val img= intent.getStringExtra("user_photoUrl")

        Picasso.get().load(img).into(profileImage)
        reviewMessageLayout.visibility= View.GONE
        profile_contact.setOnClickListener {
            reviewMessageLayout.visibility= View.VISIBLE

        }
        send_review_button.setOnClickListener {
            RMessage=   ReviewMessage.text.toString()
            if (RMessage=="null"){
                Toast.makeText(this,"Write some reviews to send us ",Toast.LENGTH_SHORT).show()}
            else{sendReview()}      }

    }

    private fun sendReview() {

            val ref= FirebaseDatabase.getInstance().getReference("MAIN/Reviews")
            val value=SaveReviewData(Rname,Remail,RMessage)
            ref.child(Rname).setValue (value).addOnSuccessListener {
                Toast.makeText(this,"Review Sent ",Toast.LENGTH_SHORT).show()

                reviewMessageLayout.visibility= View.GONE               }
                .addOnFailureListener{
                    Toast.makeText(this," Not submited",Toast.LENGTH_SHORT).show()

                }

    }
}
