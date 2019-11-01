package com.tot.itiresturant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.SignUpActivity

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: Repository = Repository(application)
    private var signUpActivity: SignUpActivity = SignUpActivity()

    fun newUser(emailAddress:String, password:String){
        repository.newUser(emailAddress, password)
    }

    fun setMsg(msg: String){
        signUpActivity.setMsg(msg)
    }
}