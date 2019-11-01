package com.tot.itiresturant.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.SignUpActivity

class SignUpViewModel(context: Context) : ViewModel() {

    private var repository: Repository = Repository(context)
    private var signUpActivity: SignUpActivity = SignUpActivity()

    fun newUser(emailAddress:String, password:String){
        repository.newUser(emailAddress, password)
    }

    fun setMsg(msg: String){
        signUpActivity.setMsg(msg)
    }
}