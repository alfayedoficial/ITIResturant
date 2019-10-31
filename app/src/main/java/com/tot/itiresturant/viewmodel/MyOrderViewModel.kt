package com.tot.itiresturant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.view.activity.CartActivity
import com.tot.itiresturant.view.fragment.MyOrderFragment

class MyOrderViewModel() : ViewModel() {

    private lateinit var mutableLiveData: MutableLiveData<List<Order>>

    private lateinit var cartActivity: CartActivity
    private lateinit var myOrderFragment: MyOrderFragment

    constructor(cartActivity: CartActivity) : this() {
        mutableLiveData = MutableLiveData<List<Order>>()
        this.cartActivity = cartActivity
    }

    constructor(myOrderFragment: MyOrderFragment) : this() {
        mutableLiveData = MutableLiveData<List<Order>>()
        this.myOrderFragment = myOrderFragment
    }


    fun getAllOrders(): LiveData<List<Order>>? {
        // fireBaseRepo.getAllOrders(mutableLiveData)
        return mutableLiveData
    }

    fun deleteOrders() {
        // fireBaseRepo.deleteOrders()
    }


    fun addOrder(order: Order) {
        // fireBaseRepo.insertOrder(order)
    }


    fun showError() {
    }

    fun showMessage() {
    }


}
