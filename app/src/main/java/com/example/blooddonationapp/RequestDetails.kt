package com.example.blooddonationapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.blooddonationapp.models.Request
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_request_details.*

class RequestDetails : AppCompatActivity(), PermissionListener {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_details)
        supportActionBar?.hide()
        var requestModel = intent.extras?.getSerializable("requestModel") as Request
        Toast.makeText(this,requestModel.hospital,Toast.LENGTH_SHORT).show()

        txt_name.text="Name :"+requestModel.userName
        txt_BloodGrp.text="Blood Group :"+requestModel.userBloodGrp
        txt_address.text="Address :"+requestModel.userLocation
        txt_hospital.text="Request From :"+requestModel.hospital
        txt_location.text="Location Of Hospital :"+requestModel.userLocation
        txt_neededBloodGrp.text="Needed BloodGroup :"+requestModel.userBloodGrp
        txt_neededUnit.text="Needed No of Unit :"+requestModel.unitNeeded
        txt_patiantName.text="Patient Name :"+requestModel.userName
        txt_phone.text="Phone No :"+requestModel.userPhone

        btn_call.setOnClickListener {
            callPaisent(requestModel.userPhone.toString())
        }
        img_call.setOnClickListener {
            callPaisent(requestModel.userPhone.toString())
        }

        //check permission
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CALL_PHONE)
            .withListener(this)
            .check()

    }

    fun callPaisent(number:String){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //permission nahi diya salene return null karde ops kotlin mai null hai hi nahi :)
            return
        }
        startActivity(intent)
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
       Toast.makeText(this,"granted permission ..",Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        TODO("Not yet implemented")
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        TODO("Not yet implemented")
    }

}