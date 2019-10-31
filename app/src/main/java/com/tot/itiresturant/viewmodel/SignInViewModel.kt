package com.tot.itiresturant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.SignInActivity
import com.tot.itiresturant.view.activity.SignUpActivity

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