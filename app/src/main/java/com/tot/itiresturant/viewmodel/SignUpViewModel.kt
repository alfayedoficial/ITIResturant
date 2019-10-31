package com.tot.itiresturant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tot.itiresturant.repo.SignUpRepository

class SignUpViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: SignUpRepository = SignUpRepository(application)

    fun newUser(emailAddress:String, password:String){
        repository.newUser(emailAddress, password)
    }
}