package com.tot.itiresturant.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order
import kotlinx.android.synthetic.main.menuitem.view.*

class HomeAdapter(val context: Context, var homeActivity: Activity): RecyclerView.Adapter<HomeAdapter.ReHolder>() {
    lateinit var menuItems:ArrayList<Order>
    init {
        menuItems=arrayListOf(Order())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReHolder {
        return ReHolder(LayoutInflater.from(context).inflate(R.layout.menuitem,parent,false))
    }

    override fun getItemCount(): Int {
        return if(menuItems!=null) menuItems.size else 0
    }

    override fun onBindViewHolder(holder: ReHolder, position: Int) {

    }

    fun setData(orders: ArrayList<Order>){
        menuItems=orders
        notifyDataSetChanged()
    }
    class ReHolder (view: View): RecyclerView.ViewHolder(view){
        val image=view.orderImage
        val name=view.txtItem
        val price=view.txtPrice

    }
}