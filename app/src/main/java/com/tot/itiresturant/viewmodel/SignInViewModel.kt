package com.tot.itiresturant.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.SignInActivity

//class SignInViewModel (application: Application) : AndroidViewModel(application) {
class SignInViewModel () : ViewModel() {
    var activity:Activity? = null
    private var repository: Repository? = null
    constructor(activity: Activity):this()
    {
        this.activity = activity
        repository = Repository(activity)
    }

    fun loginUser(emailAddress: String, password: String) {
        repository!!.loginUser(emailAddress, password)
    }

    fun setMsg(msg: String) {
        val activity:SignInActivity = activity as SignInActivity
        activity.setMsg(msg)
    }
}