package com.tot.itiresturant.repo

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpRepository (application: Application) {
    internal var mAuth: FirebaseAuth

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    fun newUser(emailAddress:String, password:String){
        mAuth.createUserWithEmailAndPassword(emailAddress, password)
    }
}