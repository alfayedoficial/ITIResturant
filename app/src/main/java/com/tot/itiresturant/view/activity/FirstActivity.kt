package com.tot.itiresturant.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tot.itiresturant.R
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        btnSignIn.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
        }

        btnSignup.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
