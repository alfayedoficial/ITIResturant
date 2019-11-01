package com.tot.itiresturant.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.SignInActivity

class SignInViewModel (context: Context) : ViewModel() {

    private var repository: Repository = Repository(context)
    private var signInActivity: SignInActivity = SignInActivity()

    fun loginUser(emailAddress: String, password: String) {
        repository.loginUser(emailAddress, password)
    }

    fun setMsg(msg: String) {
        signInActivity.setMsg(msg)
    }
}