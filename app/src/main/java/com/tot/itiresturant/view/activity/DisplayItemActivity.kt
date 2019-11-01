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
import com.bumptech.glide.Glide
import java.util.*

import android.widget.Toast


class DisplayItemActivity : AppCompatActivity() {

    private lateinit var orderData: Order
    private lateinit var orderViewModel: MyOrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_item)

        orderData = intent.extras!!.getParcelable("order")!!
        orderViewModel = MyOrderViewModel()
        initComponents()
    }

    private fun initComponents()
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
                val integers = "^[0-9]+$"
                if(s.toString().matches(Regex(integers))) {
                    val totalValue = s.toString().toInt() * orderData.price
                    total.text = totalValue.toString()
                }
                else {
                    total.text = ""
                    number_value.setText("")
                    Toast.makeText(applicationContext, R.string.valied_number,Toast.LENGTH_LONG).show()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })

        order.setOnClickListener {
            if (number_value.text.toString().isNotEmpty()) {
                orderData.id = UUID.randomUUID().toString()
                orderViewModel.addOrder(orderData)
            }
            else{
                Toast.makeText(applicationContext, R.string.order_incomplete,Toast.LENGTH_LONG).show()
            }
        }
    }
}
