package com.tot.itiresturant.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.viewmodel.MyOrderViewModel
import kotlinx.android.synthetic.main.activity_display_item.*
import kotlinx.android.synthetic.main.toolbar.*
import android.text.Editable

class DisplayItemActivity : AppCompatActivity() {

    lateinit var orderData: Order
    lateinit var orderViewModel: MyOrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_item)

        orderData = intent.extras!!.getParcelable("order")!!
        orderViewModel = MyOrderViewModel()
        initComponents()
    }

    fun initComponents()
    {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = orderData.name

        Glide.with(this)
            .load(orderData.image)
            .into(meal)
        description.text = orderData.description
        price.text = orderData.price.toString()
        number_value.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val totalValue =  s.toString().toDouble() * orderData.price
                total.text = totalValue.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })

        order.setOnClickListener { orderViewModel.addOrder(orderData) }
    }
}
