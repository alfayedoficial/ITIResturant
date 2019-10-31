package com.tot.itiresturant.repo

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tot.itiresturant.R

class SignUpRepository (val application: Application) {
    internal var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun newUser(emailAddress:String, password:String){
        mAuth.createUserWithEmailAndPassword(emailAddress, password)
            .addOnSuccessListener {
                Toast.makeText(application.applicationContext, R.string.signupSuccess, Toast.LENGTH_SHORT).show()
            }
    }
}