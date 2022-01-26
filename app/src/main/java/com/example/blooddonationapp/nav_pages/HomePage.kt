package com.example.blooddonationapp.nav_pages

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonationapp.Adapter.RequestAdapter
import com.example.blooddonationapp.R
import com.example.blooddonationapp.models.Request
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class HomePage : Fragment() {

    val toolbar:Toolbar?=null
    private lateinit var linearLayoutManager: LinearLayoutManager
    var recyclerView : RecyclerView?=null
    var adapter  : RequestAdapter?=null
    var user: FirebaseUser?=null
    var requestList:MutableList<Request>?=null
    var databaseRequest: DatabaseReference?=null


    private fun getData() {
        // requestList=null
//        var query  = databaseRequest?.child(user?.uid.toString())

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val list = ArrayList<Request>()
                if(dataSnapshot.exists()){
                    for (e in dataSnapshot.children){
                        val item =e!!.getValue(Request::class.java)
                        requestList?.add(item!!)
                    }
                    Log.e("f","finish")
                    adapter= RequestAdapter(requestList!!,context!!)
                    Log.e("r",requestList.toString())
                    recyclerView?.adapter=adapter
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        databaseRequest?.addValueEventListener(postListener)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home_page, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager=linearLayoutManager
        recyclerView?.setHasFixedSize(true)

        requestList= mutableListOf()
        databaseRequest= FirebaseDatabase.getInstance().getReference("Request")
        user= FirebaseAuth.getInstance().currentUser
        if(user!=null){
            view.findViewById<TextView>(R.id.uname).text= user?.email
            getData()
            recyclerView?.adapter=adapter
        }
        return view
    }
    companion object {
        fun newInstance(): HomePage = HomePage()
    }
}