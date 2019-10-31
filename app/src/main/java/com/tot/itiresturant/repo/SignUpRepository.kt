package com.tot.itiresturant.repo

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpRepository (application: Application) {
    internal var mAuth: FirebaseAuth

    init {
        mAuth = FirebaseAuth.getInstance()
    }
    

}