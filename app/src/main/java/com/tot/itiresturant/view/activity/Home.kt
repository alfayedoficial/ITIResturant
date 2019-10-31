package com.tot.itiresturant.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.recyclerDecoration.GridItemDecoration
import com.tot.itiresturant.view.adapter.HomeAdapter
import com.tot.itiresturant.viewmodel.OrederViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*


class Home : AppCompatActivity() {
lateinit var orderViewModel: OrederViewModel
lateinit var homeAdapter: HomeAdapter
lateinit var toolbarMenu: Toolbar
lateinit var navigationView: NavigationView
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
        navigationView=navigation_home
        setToolBar()
        setListener()
    }


    private fun setToolBar() {
        toolbarMenu=toolbar
        setSupportActionBar(toolbarMenu)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_foreground)
        toolbarMenu.title="Menu"
    }
    private fun setListener() {
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_Item_Profile -> { }
                R.id.menu_item_myOrder->{}
                R.id.menu_item_setting->{}

            }
            true

        }
    }
}
