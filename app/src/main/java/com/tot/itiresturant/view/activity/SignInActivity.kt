package com.tot.itiresturant.view.activity

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tot.itiresturant.R
import com.tot.itiresturant.viewmodel.SignInViewModel
import com.tot.itiresturant.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignInActivity : AppCompatActivity() {

    private lateinit var signinViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signinViewModel = ViewModelProviders.of(this,MyViewModelFactory(this)).get(SignInViewModel::class.java)
        btn_sign_in.setOnClickListener{
            signIn()
        }
    }

    private fun signIn(){
        if (emailSignIn.text.toString().trim().isEmpty()) {
            emailSignIn.error = getText(R.string.emailRequired)
            emailSignIn.requestFocus()
        } else if (passwordSignIn.text.toString().trim().isEmpty()){
            passwordSignIn.error = getText(R.string.passwordRequired)
            passwordSignIn.requestFocus()
        } else if (!isNetworkConnected()){
            Toast.makeText(this, R.string.networkError, Toast.LENGTH_SHORT).show()
        } else {
            signinViewModel.loginUser(emailAddress.text.toString().trim(), password.text.toString().trim())
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    fun setMsg(msg: String){
        if (msg == "signIn success"){
            Toast.makeText(this, R.string.signinSuccess, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Home::class.java))
        } else {
            Toast.makeText(this, R.string.errorSignIn, Toast.LENGTH_SHORT).show()
        }
    }

    internal class MyViewModelFactory(var activity: Activity) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignInViewModel(activity) as T
        }
    }
}
