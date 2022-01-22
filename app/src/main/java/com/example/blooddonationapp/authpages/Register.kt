package com.example.blooddonationapp.authpages

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapp.DashBorad
import com.example.blooddonationapp.R
import com.example.blooddonationapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*





class Register : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null

    private lateinit var auth: FirebaseAuth

    var email:String?=""; var password:String?="" ;var phoneno:String?=""


    override fun onStart() {
        super.onStart()
        auth= FirebaseAuth.getInstance()
        val user=auth.currentUser
        if(user!=null){
            val intent = Intent(this,DashBorad::class.java) ;startActivity(intent)
        }
        else{

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //init firebase
        auth= FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        text_login.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish()

        }

       button_register.setOnClickListener {
           email= edit_email.text.toString() ;phoneno= edit_phone.text.toString();password= edit_password.text.toString()
           val isRight= validate(email!!, phoneno!!, password!!)
           if(isRight)
           {
               registerUser(email!!, phoneno!!, password!!)
           }
       }
    }

    private fun registerUser(email: String, phoneno: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                val uid= user?.uid

                //store user data  to database
                val userdata=User(uid!!,"sample name",password,email,phoneno,"not define","no grup","no address","not define")
                mDatabase!!.child(uid!!).setValue(userdata)

                Toast.makeText(this, "Logined As$uid", Toast.LENGTH_SHORT).show()

                //goto home page
                val intent = Intent(this,DashBorad::class.java) ;startActivity(intent)

            } else {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

//            Toast.makeText(this, "good your in", Toast.LENGTH_SHORT).show()

        }

    }

    private fun validate(email: String, phoneno: String, password: String) :Boolean{
       return when {
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this,"Please valid email",Toast.LENGTH_SHORT).show()
                false
            }
            phoneno.length<10 -> {
                Toast.makeText(this,"Please valid phoneno",Toast.LENGTH_SHORT).show()
                 false
            }
            password.length<6 -> {
                Toast.makeText(this,"password must atleast content",Toast.LENGTH_SHORT).show()
                 false
            }
            else -> {
                 true
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}