package com.tot.itiresturant.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.recyclerDecoration.GridItemDecoration
import com.tot.itiresturant.view.adapter.HomeAdapter
import com.tot.itiresturant.viewmodel.OrederViewModel
import kotlinx.android.synthetic.main.activity_home.*


class Home : AppCompatActivity() {
lateinit var orderViewModel: OrederViewModel
lateinit var homeAdapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        intiComponent()
    }

    private fun intiComponent() {
        orderViewModel= ViewModelProviders.of(this).get(OrederViewModel::class.java)
        home_RecyclerView.layoutManager=GridLayoutManager(this,2)
        home_RecyclerView.addItemDecoration(GridItemDecoration(10, 2))
        homeAdapter= HomeAdapter(this,this)
        home_RecyclerView.adapter=homeAdapter
        orderViewModel.getAllMenuItems().observe(this, Observer {
            homeAdapter.setData(it as ArrayList<Order>)
            homeAdapter.notifyDataSetChanged()
        })
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator()
    }
}
