package com.amanaryan.firebase_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_tutor_message.*
import kotlinx.android.synthetic.main.messageviewholdwe.view.*

class TutorMessage : AppCompatActivity() {
    var username=""
    var useremail=""
    var userimg=""
    var id=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_message)

        username= intent.getStringExtra("user_name")
        useremail= intent.getStringExtra("user_email")
        userimg= intent.getStringExtra("user_photoUrl")
       // id=intent.getStringExtra("user_id")



        Tm_profile.setOnClickListener {

            val i=Intent(this,profolio::class.java)
            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)

            startActivity(i)
        }
        Registration_TutorForm.setOnClickListener {

            val i=Intent(this,upload_Image::class.java)
            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)

            startActivity(i)
        }

        val adapte= GroupAdapter<ViewHolder>()
        MessageRecyclerView.adapter=adapte
        adapte.add(Mplaceholder())
        RecieveMessage()

    }

    fun RecieveMessage(){

        val i= Intent(this,Messages::class.java)
//        MAIN/LOGIN/TUTORS/ ${TutorName}/${TutorMail}/MESSAGES
        val reffo=FirebaseDatabase.getInstance().getReference("MAIN/MESSAGES/TUTORS/${username}/MESSAGES")
        reffo.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adap=GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    val mesg=it.getValue(SaveMessage::class.java)
                    Log.d("save",it.toString())

                    adap.setOnItemClickListener{item,view->
                        val MessageItem= item as OOrder

                        i.putExtra("metaData_MessageStudent_Name",MessageItem.data.sname)
                        i.putExtra("metaData_MessageStudent_Email",MessageItem.data.semail)
                        i.putExtra("metaData_MessageStudent_Message",MessageItem.data.smessage)
                        i.putExtra("metaData_MessageStudent_phone",MessageItem.data.sphone)

                        i.putExtra("user_id",id)
                        startActivity(i)
                    }
                    if(mesg!=null){
                        adap.add(OOrder(mesg))
                        Log.d("yaha v","yaha aa gya")
                    }
                    MessageRecyclerView.adapter=adap
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}



class OOrder(val data : SaveMessage): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.messageviewholdwe
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Log.d("order me aaya h", "order me")
        viewHolder.itemView.message_name.text = data.sname
        viewHolder.itemView.message_phone.text = data.sphone
    }
}
class Mplaceholder:Item<ViewHolder>()   {

    override fun getLayout(): Int {

        return R.layout.messageplaceholder

    }



    override fun bind(viewHolder: ViewHolder, position: Int) {



    }



}