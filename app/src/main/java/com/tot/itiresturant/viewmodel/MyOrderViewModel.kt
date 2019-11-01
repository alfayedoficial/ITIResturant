package com.tot.itiresturant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.CartActivity
import com.tot.itiresturant.view.fragment.MyOrderFragment

class MyOrderViewModel() : ViewModel() {

    private lateinit var mutableLiveData: MutableLiveData<List<Order>>

    private lateinit var cartActivity: CartActivity
    private lateinit var myOrderFragment: MyOrderFragment
    private val repo:Repository = Repository()

    constructor(cartActivity: CartActivity) : this() {
        mutableLiveData = MutableLiveData()
        this.cartActivity = cartActivity
    }

    constructor(myOrderFragment: MyOrderFragment) : this() {
        mutableLiveData = MutableLiveData()
        this.myOrderFragment = myOrderFragment
    }


    fun getAllOrders(): LiveData<List<Order>>? {
        mutableLiveData.postValue(repo.getAllOrdersOrderViewModel())
        return mutableLiveData
    }

    fun deleteOrder(order: Order) {
         repo.deleteOrder(order)
    }

    fun deleteOrders() {
        repo.deleteOrders()
    }

    fun addOrder(order: Order) {
         repo.insertOrder(order)
    }

    fun updateOrder(order: Order){
        repo.updateOrder(order)
    }

    fun showError() {
    }

    fun showMessage() {
    }


}
