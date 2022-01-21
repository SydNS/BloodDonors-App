package com.example.blooddonationapp.nav_pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonationapp.Adapter.RequestAdapter
import com.example.blooddonationapp.R
import com.example.blooddonationapp.models.Request
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

class Profile_Page : Fragment() {


    val toolbar: Toolbar?=null
    private lateinit var linearLayoutManager: LinearLayoutManager
    var recyclerView : RecyclerView?=null
    var adapter  : RequestAdapter?=null
    var user: FirebaseUser?=null
    var requestList:MutableList<Request>?=null
    var databaseRequest: DatabaseReference?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile__page, container, false)
    }

    companion object {
        fun newInstance(): Profile_Page = Profile_Page()
    }
}