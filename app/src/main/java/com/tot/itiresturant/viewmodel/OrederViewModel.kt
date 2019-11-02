package com.tot.itiresturant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.repo.Repository
import com.tot.itiresturant.view.activity.Home

class OrederViewModel : ViewModel()  {
    lateinit var repository:Repository
    lateinit var myHome: Home
    fun setHome(home: Home){
        myHome=home
        repository= Repository(myHome)
    }
    fun getAllMenuItems(): MutableLiveData<ArrayList<Order>> {
        return repository.getAllOrders()
    }

}