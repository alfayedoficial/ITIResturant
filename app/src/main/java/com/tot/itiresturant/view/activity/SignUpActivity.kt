package com.tot.itiresturant.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tot.itiresturant.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.tot.itiresturant.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var signupViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signupViewModel = SignUpViewModel(applicationContext)

        btn_sign_up.setOnClickListener{
            signUp()
        }
    }

    private fun signUp(){
        if (emailAddress.text.toString().trim().isEmpty()) {
            emailAddress.error = getText(R.string.emailRequired)
            emailAddress.requestFocus()
        } else if (password.text.toString().trim().isEmpty()){
            password.error = getText(R.string.passwordRequired)
            password.requestFocus()
        } else if (password.text.toString().trim().length < 6){
            password.error = getText(R.string.lengthPassword)
            password.requestFocus()
        } else if (confPassword.text.toString().trim().isEmpty()){
            confPassword.error = getText(R.string.confPasswordRequired)
            confPassword.requestFocus()
        } else if (password.text.toString().trim() != confPassword.text.toString().trim()){
            Toast.makeText(this, R.string.incompatiblePassword, Toast.LENGTH_SHORT).show()
        } else if (!isNetworkConnected()){
            Toast.makeText(this, R.string.networkError, Toast.LENGTH_SHORT).show()
        } else {
            signupViewModel.newUser(emailAddress.text.toString().trim(), password.text.toString().trim())
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    fun setMsg(msg: String){
        if (msg == "signUp success"){
            Toast.makeText(this, R.string.signupSuccess, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SignInActivity::class.java))
        } else {
            Toast.makeText(this, R.string.errorSignUp, Toast.LENGTH_SHORT).show()
        }
    }
}
