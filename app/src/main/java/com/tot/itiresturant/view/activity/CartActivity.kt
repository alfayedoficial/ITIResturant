package com.tot.itiresturant.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.view.adapter.CartAdapter
import com.tot.itiresturant.viewmodel.MyOrderViewModel
import kotlin.Double as Double1

class CartActivity : AppCompatActivity() {

    lateinit var cart_recycler: RecyclerView
    lateinit var cart_adapter: CartAdapter
    lateinit var textViewPrice: TextView
    lateinit var textViewTax: TextView
    lateinit var textViewTotal: TextView
    lateinit var btnPayNow: Button
    lateinit var orderViewModel: MyOrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        orderViewModel = ViewModelProviders.of(this, OrdersViewModelFactory(this))
            .get(orderViewModel::class.java!!)

        val layoutManager = LinearLayoutManager(this)
        cart_recycler.layoutManager = layoutManager
        cart_adapter = CartAdapter(this)
        cart_recycler.adapter = cart_adapter



        btnPayNow.setOnClickListener { orderViewModel.deleteOrders() }


    }


    fun showMessage() {
        updateRecyclerView()
    }

    public override fun onStart() {
        super.onStart()
        updateRecyclerView()
    }

    fun updateRecyclerView() {
        orderViewModel.getAllOrders()?.observe(this,
            Observer<List<Order>> { orders ->
                calcPrice(orders)
                cart_adapter.setData(orders)
            })
    }

    private fun calcPrice(orders: List<Order>?) {

        var allPrice = 0.0
        var tax: Double1
        var total: Double1

        for (order in orders!!) {
            allPrice += order.price
        }

        textViewPrice.text = "" + allPrice

        tax = allPrice * 0.14
        textViewTax.text = "" + tax

        total = tax + allPrice
        textViewTotal.text = "" + total


    }

    class OrdersViewModelFactory(private val cartActivity: CartActivity) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OrdersViewModelFactory(cartActivity) as T
        }
    }

}
