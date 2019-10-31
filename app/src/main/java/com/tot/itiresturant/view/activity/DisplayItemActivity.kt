package com.tot.itiresturant.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order

class DisplayItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_item)

        val orderData: Order = intent.extras!!.getParcelable("order")!!
    }

    fun initComponents()
    {

    }
}
