package com.example.blooddonationapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonationapp.R
import com.example.blooddonationapp.RequestDetails
import com.example.blooddonationapp.authpages.Register
import com.example.blooddonationapp.models.Request
import kotlinx.android.synthetic.main.row_request.view.*
import java.io.Serializable

class RequestAdapter(private val requestList:MutableList<Request>,val context: Context) : RecyclerView.Adapter<RequestAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.row_request,parent,false)
        return MyHolder(itemview)
    }
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       val currentItem =requestList[position]
        holder.txt_BloodGrp.text="Blood :"+currentItem.userBloodGrp
        holder.txt_need.text="Unit Need :"+currentItem.unitNeeded
        holder.txt_name.text=currentItem.userName
        holder.btn_donateBlood.setOnClickListener {
            val intent = Intent(context, RequestDetails::class.java)
            intent.putExtra("requestModel",currentItem as Serializable)
            context.startActivity(intent)
        }
    }


    override fun getItemCount()=requestList.size

    class MyHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        var txt_name:TextView=view.txt_name
        var txt_need:TextView=view.txt_need
        var txt_BloodGrp:TextView=view.txt_BloodGrp
        var btn_donateBlood:Button=view.btn_donateBlood
        init {
            v.setOnClickListener(this)
        }
        override fun onClick(v: View) {
//            Log.d("RecyclerView", "CLICK!")
//            val intent = Intent(v.context, RequestDetails::class.java)
//            v.context.startActivity(intent)
        }



        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }


}