package com.tot.itiresturant.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.SignInActivity
import com.tot.itiresturant.view.activity.SignUpActivity

//class SignUpViewModel(application: Application) : AndroidViewModel(application) {
class SignUpViewModel() : ViewModel() {
    var activity: Activity? = null
    private var repository: Repository? = null
    constructor(activity: Activity):this(){
        this.activity = activity
        repository = Repository(activity)
    }

    fun newUser(emailAddress:String, password:String){
        repository!!.newUser(emailAddress, password)
    }

    fun setMsg(msg: String){
        val activity: SignUpActivity = activity as SignUpActivity
        activity.setMsg(msg)
    }
}