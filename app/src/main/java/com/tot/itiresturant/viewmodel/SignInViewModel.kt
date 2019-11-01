package com.tot.itiresturant.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.SignInActivity

class SignInViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: Repository = Repository(application)
    private var signInActivity: SignInActivity = SignInActivity()

    fun loginUser(emailAddress: String, password: String) {
        repository.loginUser(emailAddress, password)
    }

    fun setMsg(msg: String) {
        signInActivity.setMsg(msg)
    }
}