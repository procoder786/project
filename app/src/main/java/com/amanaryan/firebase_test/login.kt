package com.amanaryan.firebase_test


import android.content.DialogInterface



import androidx.appcompat.app.AppCompatActivity







import android.content.Intent



import android.net.Uri



import android.os.Bundle



import android.util.Log



import android.view.View

import android.view.animation.Animation

import android.view.animation.AnimationUtils



import android.widget.Button



import android.widget.Toast



import android.widget.Toast.LENGTH_SHORT



import androidx.annotation.IntegerRes

import androidx.constraintlayout.widget.ConstraintLayout



import androidx.core.content.getSystemService







import com.google.android.gms.auth.api.signin.GoogleSignIn



import com.google.android.gms.auth.api.signin.GoogleSignInAccount



import com.google.android.gms.auth.api.signin.GoogleSignInClient



import com.google.android.gms.auth.api.signin.GoogleSignInOptions



import com.google.android.gms.common.SignInButton



import com.google.android.gms.common.api.ApiException



import com.google.android.gms.tasks.OnCompleteListener



import com.google.android.gms.tasks.Task



import com.google.firebase.auth.AuthCredential



import com.google.firebase.auth.AuthResult



import com.google.firebase.auth.FirebaseAuth



import com.google.firebase.auth.FirebaseUser



import com.google.firebase.auth.GoogleAuthProvider



import com.google.firebase.database.FirebaseDatabase



import kotlinx.android.synthetic.main.activity_login.*



import kotlin.system.exitProcess


class login : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient





    private var mAuth: FirebaseAuth? = null

    var Loginas="STUDENTS"



    var personGivenName="DefaultUserName"

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_login)







        progress_barId.visibility = View.GONE



        //progress bar





        var profilesignout = intent.getStringExtra("signoutcheck")















        mAuth = FirebaseAuth.getInstance()





        // Configure Google Sign In





        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)





            .requestIdToken(getString(R.string.default_web_client_id))





            .requestEmail()





            .build()





        // Build a GoogleSignInClient with the options specified by gso.





        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)



        var m = 0



        if (profilesignout == "yes") {



            mAuth!!.signOut()



            m = 2

//

//            startActivity(Intent(this, splashActivity::class.java))





        }





        //check user login





        val anim=findViewById<ConstraintLayout>(R.id.loginlayout)

        val animation: Animation =AnimationUtils.loadAnimation(this,R.anim.fade_in)

        anim.startAnimation(animation)


        TermAndConditions.setOnClickListener {

            startActivity(Intent(this,TermsConditions_photos::class.java))
        }

        sign_inforStudents_button.setOnClickListener {



            LoginLay.visibility = View.GONE



            progress_barId.visibility = View.VISIBLE



            signIn()

        }









        loginAsTutor.setOnClickListener {

            Loginas="TUTORS"

            LoginLay.visibility = View.GONE
            progress_barId.visibility = View.VISIBLE



            signIn()

        }







    }





    private fun signIn() {





        val signInIntent = mGoogleSignInClient.signInIntent







        startActivityForResult(signInIntent, RC_SIGN_IN)





    }





    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {





        super.onActivityResult(requestCode, resultCode, data)





        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);





        if (requestCode == RC_SIGN_IN) {





            val task = GoogleSignIn.getSignedInAccountFromIntent(data)







            try {





                // Google Sign In was successful, authenticate with Firebase





                val account = task.getResult(ApiException::class.java)







                firebaseAuthWithGoogle(account!!)





            } catch (e: ApiException) {





                // Google Sign In failed, update UI appropriately





                Log.w(TAG, "Google sign in failed", e)





                // ...





            }





        }





    }





    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {





        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)





        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)







        mAuth!!.signInWithCredential(credential)





            .addOnCompleteListener(this) { task ->



                if (task.isSuccessful) {





                    // Sign in success, update UI with the signed-in user's information





                    Log.d(TAG, "signInWithCredential:success")





                    val user = mAuth!!.currentUser







                    updateUI(user)





                } else {





                    // If sign in fails, display a message to the user.





                    Log.w(TAG, "signInWithCredential:failure", task.exception)







                    Toast.makeText(



                        this@login,



                        "you are not able to login. Try after some Time,",



                        Toast.LENGTH_LONG



                    ).show()













                }





                // ...



            }





    }





    private fun updateUI(user: FirebaseUser?) {





        val acct = GoogleSignIn.getLastSignedInAccount(applicationContext)



        var user_name: String = ""



        var user_email: String = ""



        var user_photoUrl: String = ""



        if (acct != null) {





            val personName = acct.displayName.toString()



            user_name = personName



             personGivenName = acct.givenName.toString()





            val personFamilyName = acct.familyName.toString()





            val personEmail = acct.email.toString()







            user_email = personEmail



            val personId = acct.id.toString()





            val personPhoto = acct.photoUrl.toString()



            user_photoUrl = personPhoto
















            val ref = FirebaseDatabase.getInstance().getReference("MAIN/LOGIN/${Loginas}/${personName}")
//            MAIN/LOGIN/TUTORS/ ${TutorName}/${TutorMail}/MESSAGES




            val value = SaveUserData(personName,personEmail, personId,personGivenName, personFamilyName, personPhoto )







            ref.child(personName).setValue(value).addOnSuccessListener {



                Log.d("lele", "please")



                //getData()








            }





        }

        if(Loginas=="STUDENTS") {





            var i = Intent(this, filter::class.java)


            i.putExtra("user_name", user_name)
            i.putExtra("user_email", user_email)
            i.putExtra("user_photoUrl", user_photoUrl)
            i.putExtra("intentNumber","1")
            startActivity(i)

        }



        if(Loginas=="TUTORS") {





            var i = Intent(this, TutorMessage::class.java)





            i.putExtra("user_name", user_name)



            i.putExtra("user_email", user_email)



            i.putExtra("user_photoUrl", user_photoUrl)







            startActivity(i)

        }

    }





    companion object {





        val RC_SIGN_IN = 1





        val TAG = "MainActivity"



    }



}