package com.example.blooddonationapp.authpages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.blooddonationapp.DashBorad
import com.example.blooddonationapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edit_email
import kotlinx.android.synthetic.main.activity_login.edit_password

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    var email:String?=""; var password:String?=""


    override fun onStart() {
        super.onStart()
        auth= FirebaseAuth.getInstance()
        val user=auth.currentUser
        if(user!=null){
            val intent = Intent(this, DashBorad::class.java) ;startActivity(intent)
            finish()
        }
        else{

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //init firebase
        auth= FirebaseAuth.getInstance()

        text_register.setOnClickListener {
            val intent = Intent(this,Register::class.java) ;startActivity(intent)
            finish()

        }

        button_login.setOnClickListener {
            email= edit_email.text.toString();password= edit_password.text.toString()
            val isRight= validate(email!!,  password!!)
            if(isRight)
            {
                loginUser(email!!,  password!!)
            }
        }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right)
    }

    private fun loginUser(email: String,  password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                val uid= user?.uid
                Toast.makeText(this, "Logined As$uid", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,DashBorad::class.java) ;startActivity(intent)
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            Toast.makeText(this, "good your in", Toast.LENGTH_SHORT).show()

        }
    }

    private fun validate(email: String,  password: String) :Boolean{
         return when {
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this,"Please valid email", Toast.LENGTH_SHORT).show()
                false
            }
            password.length<6 -> {
                Toast.makeText(this,"password must atleast content", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

}