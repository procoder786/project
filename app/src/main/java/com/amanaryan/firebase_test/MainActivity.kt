package com.amanaryan.firebase_test

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewholder.view.*
import java.net.NetworkInterface

class MainActivity : AppCompatActivity() {
var classs=""
    var sub=""
    var loc=""
    lateinit var metaName:String


//    override fun onNetworkConnectionChanged(isConnected: Boolean) {
//
//
//
//        if (isConnected){
//
//            Toast.makeText(this,"connected",Toast.LENGTH_LONG).show()
//
//            //dashboardWithAllHostels()
//
//        }else
//
//            Toast.makeText(this,"Network Not Connected",Toast.LENGTH_LONG).show()
//
//    }//internet connectio

    var username= ""
    var useremail= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
checkInternet()


         username= intent.getStringExtra("user_name")
        useremail= intent.getStringExtra("user_email")
        val userimg= intent.getStringExtra("user_photoUrl")






        val adapte=GroupAdapter<ViewHolder>()
        recyclerviewwIndoor.adapter=adapte
        adapte.add(placeholder())
        adapte.add(placeholder())
        adapte.add(placeholder())
        adapte.add(placeholder())
        adapte.add(placeholder())
        val adap=GroupAdapter<ViewHolder>()
        recyclervieww.adapter=adap
        adap.add(placeholder())
        adap.add(placeholder())
        adap.add(placeholder())
        adap.add(placeholder())
        adap.add(placeholder())

        classs=intent.getStringExtra("class")
        sub=intent.getStringExtra("subject")
         loc=intent.getStringExtra("location")
        Toast.makeText(this,"Location: ,  ${loc}",Toast.LENGTH_LONG).show()
        recyclervieww.visibility=View.GONE
        indoorBlack.visibility=View.GONE
        outdoorWhite.visibility=View.GONE

        outdoorBlack.setOnClickListener {
            outdoorWhite.visibility=View.VISIBLE
            indoorWhite.visibility=View.GONE
            indoorBlack.visibility=View.VISIBLE
            outdoorBlack.visibility=View.GONE
            recyclervieww.visibility=View.VISIBLE
            recyclerviewwIndoor.visibility=View.GONE
        }

        indoorBlack.setOnClickListener {
            outdoorWhite.visibility=View.GONE
            indoorWhite.visibility=View.VISIBLE
            indoorBlack.visibility=View.GONE
            outdoorBlack.visibility=View.VISIBLE
            recyclerviewwIndoor.visibility=View.VISIBLE
            recyclervieww.visibility=View.GONE
        }
        filter.setOnClickListener {

            val i =Intent(this,com.amanaryan.firebase_test.filter::class.java)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
            startActivity(i)

        }
        getOutdoorData()
        getIndoorData()


        profile.setOnClickListener {
            val i =Intent(this,profolio::class.java)

            i.putExtra("user_name",username)
            i.putExtra("user_email",useremail)
            i.putExtra("user_photoUrl",userimg)
        startActivity(i)

        }

    }
    fun getOutdoorData(){

        var i= Intent(this,DetailedItemView::class.java)

        val reffo=FirebaseDatabase.getInstance().getReference("MAIN/TUTOR/OUTDOOR/${classs}/${sub}/${loc}")
        reffo.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adap=GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    val mer=it.getValue(SaveData::class.java)
                    Log.d("save",it.toString())

                    adap.setOnItemClickListener{item,view->
                        val tutionItem= item as Order

                        i.putExtra("metaData_name",tutionItem.data.name)
                        i.putExtra("metaData_image",tutionItem.data.imagee)
                        i.putExtra("metaData_fee",tutionItem.data.fee)
                        i.putExtra("metaData_mobile",tutionItem.data.mobile)
                        i.putExtra("metaData_aadhar",tutionItem.data.aadhar)
                        i.putExtra("metaData_qualification",tutionItem.data.qualification)
                        i.putExtra("metaData_age",tutionItem.data.age)
                        i.putExtra("metaData_gender",tutionItem.data.gender)
                        i.putExtra("metaData_email",tutionItem.data.emailid)
                        i.putExtra("metaData_address",tutionItem.data.addresss)
                        i.putExtra("metaData_description",tutionItem.data.description)
                        i.putExtra("metaData_experience",tutionItem.data.experience)
                        i.putExtra("metaData_place",tutionItem.data.place)
                        i.putExtra("metaData_clasupto",tutionItem.data.clasupto)
                        i.putExtra("metaData_subject",tutionItem.data.subject)
                        i.putExtra("metaData_nearestloc",tutionItem.data.nearestlocation)
                        i.putExtra("metaData_lolipop","outdoor")

i.putExtra("Tutor_name",tutionItem.data.username)

                        i.putExtra("Student_name",username)
                        i.putExtra("Student_email",useremail)
                        startActivity(i)
                    }
                     if(mer!=null){
                        adap.add(Order(mer))
                        Log.d("yaha v","yaha aa gya")
                    }else{
                         nodata()
                     }
                    recyclervieww.adapter=adap
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun getIndoorData() {


        var i= Intent(this,DetailedItemView::class.java)


        val reff=FirebaseDatabase.getInstance().getReference("MAIN/TUTOR/INDOOR/${classs}/${sub}/${loc}")
        reff.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter=GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    val mera=it.getValue(SaveData::class.java)
                    Log.d("save",it.toString())

                    adapter.setOnItemClickListener{item,view->
                        val tutionItem= item as Order

                        i.putExtra("metaData_name",tutionItem.data.name)
                        i.putExtra("metaData_image",tutionItem.data.imagee)
                        i.putExtra("metaData_fee",tutionItem.data.fee)
                        i.putExtra("metaData_mobile",tutionItem.data.mobile)
                        i.putExtra("metaData_aadhar",tutionItem.data.aadhar)
                        i.putExtra("metaData_qualification",tutionItem.data.qualification)
                        i.putExtra("metaData_age",tutionItem.data.age)
                        i.putExtra("metaData_gender",tutionItem.data.gender)
                        i.putExtra("metaData_email",tutionItem.data.emailid)
                        i.putExtra("metaData_address",tutionItem.data.addresss)
                        i.putExtra("metaData_description",tutionItem.data.description)
                        i.putExtra("metaData_experience",tutionItem.data.experience)
                        i.putExtra("metaData_place",tutionItem.data.place)
                        i.putExtra("metaData_clasupto",tutionItem.data.clasupto)
                        i.putExtra("metaData_subject",tutionItem.data.subject)
                        i.putExtra("metaData_nearestloc",tutionItem.data.nearestlocation)


                        i.putExtra("Tutor_name",tutionItem.data.username)
                        i.putExtra("Student_name",username)
                        i.putExtra("Student_email",useremail)
                        i.putExtra("metaData_lolipop","indoor")
                        startActivity(i)
                    }
                    if(mera!=null){
                        adapter.add(Order(mera))
                        Log.d("yaha v","yaha aa gya")
                    }else{
                        nodata()
                    }
                    recyclerviewwIndoor.adapter=adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
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

    fun nodata(){

        Toast.makeText(this,"Sorry We dont have any data in this section please use filter to change section",Toast.LENGTH_LONG).show()
    }
}

class Order(val data : SaveData): Item<ViewHolder>() {
    override fun getLayout(): Int {
    return R.layout.viewholder
}
    override fun bind(viewHolder: ViewHolder,position:Int){
        Log.d("order me aaya h","order me")
        val gennderchck=data.gender

        val imgchck=data.imagee
        if(imgchck=="null") {
            if (gennderchck == "MALE") {
                viewHolder.itemView.imageviewmale.visibility=View.VISIBLE
                viewHolder.itemView.imageviewfemale.visibility=View.GONE
            }
            if (gennderchck == "FEMALE") {
                viewHolder.itemView.imageviewmale.visibility=View.GONE
                viewHolder.itemView.imageviewfemale.visibility=View.VISIBLE
            }
        }else{
            viewHolder.itemView.imageviewmale.visibility=View.GONE
            viewHolder.itemView.imageviewfemale.visibility=View.GONE

            Picasso.get().load(data.imagee).into(viewHolder.itemView.imageview)}



        //experience
        val exp=data.experience
        val sub=data.subject
        viewHolder.itemView.textviewExperience.text="Experience :"+exp+" Yr"
        viewHolder.itemView.textviewname.text=data.name

        viewHolder.itemView.textviewLocation.text=data.nearestlocation
        viewHolder.itemView.textviewSubject.text="Subject :"+sub
        viewHolder.itemView.ttextviewfee.text=data.fee


    }


}
class placeholder:Item<ViewHolder>()   {

    override fun getLayout(): Int {

        return R.layout.layout_placeholder_recycleritems

    }



    override fun bind(viewHolder: ViewHolder, position: Int) {



    }



}
