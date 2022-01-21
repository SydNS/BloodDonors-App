package com.example.blooddonationapp.nav_pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.blooddonationapp.DashBorad
import com.example.blooddonationapp.R
import com.example.blooddonationapp.authpages.Register
import com.example.blooddonationapp.models.Request
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add__request__page.*


class Add_Request_Page : Fragment(), RadioGroup.OnCheckedChangeListener {

    var edit_name: EditText?=null
    var edit_age: EditText?=null
    var edit_phone: EditText?=null
    var edit_hospital: EditText?=null
    var edit_userLocation: EditText?=null

    var bloodGrp:String?=null
    var unitNedded:Int?=0
    var name:String?=null ;var age:String?=null;var phone:String?=null;var hospital:String?=null;var userLocation:String?=null

    //user details
    var firebaseUser :FirebaseUser?=null
    var firebaseAuth:FirebaseAuth?=null
    var userId:String?=null
    var userEmail:String?=null

    //database for Request
    private var mDatabaseRequest: DatabaseReference? = null

    override fun onStart() {
        super.onStart()

        //init firebase
        mDatabaseRequest=FirebaseDatabase.getInstance().getReference("Request")

        firebaseAuth= FirebaseAuth.getInstance()
        firebaseUser=firebaseAuth?.currentUser
        if(firebaseUser!=null){
          userId=firebaseUser?.uid
          userEmail=firebaseUser?.email
        }
        else{
            val intent = Intent(context, Register::class.java) ;startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add__request__page, container, false)
        // Get radio group selected item using on checked change listener

        //init all view
        edit_name=view.findViewById<EditText>(R.id.edit_name)
        edit_age=view.findViewById<EditText>(R.id.edit_age)
        edit_phone=view.findViewById<EditText>(R.id.edit_phone)
        edit_hospital=view.findViewById<EditText>(R.id.edit_hospital)
        edit_userLocation=view.findViewById<EditText>(R.id.edit_userLocation)

        val radioGroups = view.findViewById<RadioGroup>(R.id.radio_group)
        radioGroups.setOnCheckedChangeListener(this)
        val radioGroups2 = view.findViewById<RadioGroup>(R.id.radio_group2)
        radioGroups2.setOnCheckedChangeListener(this)

        val publishBtn =view.findViewById<Button>(R.id.publish)
        publishBtn.setOnClickListener {
            validateAllView()
//            edit_name?.error = "name is  empty"
        }

        val unitText =view.findViewById<TextView>(R.id.unitText)
        val btnAddUnit=view.findViewById<Button>(R.id.btn_addUnit)
        btnAddUnit.setOnClickListener {
            if (unitNedded!! <=20){
                unitNedded= unitNedded!! +1
                unitText.text=unitNedded.toString()
            }
        }
        val btn_removeUnit=view.findViewById<Button>(R.id.btn_removeUnit)
        btn_removeUnit.setOnClickListener {
            if (unitNedded!!>0){
                unitNedded= unitNedded!! -1
                unitText.text=unitNedded.toString()
            }
        }
    return view
    }

    private fun validateAllView() {
        name=edit_name?.text.toString()
        age=edit_age?.text.toString()
        phone=edit_phone?.text.toString()
        hospital=edit_hospital?.text.toString()
        userLocation=edit_userLocation?.text.toString()
        if(name.equals("")){
            edit_name?.error = "name is  empty"
        }
        else if(age.equals("")){
            edit_age?.error = "age is  empty"
        }
        else if(phone.equals("")){
            edit_phone?.error="phone is empty"
        }
        else if(hospital.equals("")){
            edit_hospital?.error="hospital is empty"
        }
        else if(userLocation.equals("")){
            edit_userLocation?.error="location is empty"
        }
        else if(unitNedded==0){
            Toast.makeText(context,"Select how many unitNeeded?",Toast.LENGTH_SHORT).show()
        }
        else if(bloodGrp==null){
            Toast.makeText(context,"Select how many unitNeeded?",Toast.LENGTH_SHORT).show()
        }
        else{
           //Todo send request to firebase
            Toast.makeText(context ,"bela chao bela chao",Toast.LENGTH_SHORT).show()
            sendRequestFirebase()
        }

    }

    private fun sendRequestFirebase() {
        //create request model
        val requestModel:Request = Request(name,age,phone,userLocation,hospital,unitNedded.toString(),bloodGrp,userId,userEmail)
        mDatabaseRequest!!.child(userId!!).setValue(requestModel)
        Toast.makeText(context, "Request Submitted As$userEmail", Toast.LENGTH_SHORT).show()

        //goto home page
        val intent = Intent(context, DashBorad::class.java) ;startActivity(intent)

    }

    companion object {
        fun newInstance(): Add_Request_Page = Add_Request_Page()
    }

    override fun onCheckedChanged(group: RadioGroup?, p1: Int) {
        val checkedRadioButton = group?.findViewById(group.checkedRadioButtonId) as? RadioButton
        checkedRadioButton?.let {
            if (checkedRadioButton.isChecked){
                bloodGrp= checkedRadioButton.text.toString()
            }
                Toast.makeText(context, "RadioGroup: ${group.contentDescription} RadioButton: ${checkedRadioButton.text}", Toast.LENGTH_LONG).show()
        }
    }
}