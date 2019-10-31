package com.tot.itiresturant.view.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order
import kotlinx.android.synthetic.main.myorder_row.view.*

class OrderAdapter: RecyclerView.Adapter<OrderAdapter.HolderClass>() {
    private var context: Context? = null
    private var ordersList: List<Order>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
        context = parent.getContext()
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.myorder_row, parent, false)
        return HolderClass(view)

    }

    override fun getItemCount(): Int {
        return if (ordersList != null) ordersList!!.size else 0
    }

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
        val order :Order= ordersList!!.get(position)
        holder.orderName.setText(order.name)
        holder.orderDate.setText(order.date)
        holder.orderPrice.setText(order.price.toString())
        holder.orderImage.setImageURI(order.image as Uri)

    }
    fun setData(noteList: List<Order>) {
        this.ordersList = noteList
        notifyDataSetChanged()

    }


    class HolderClass : RecyclerView.ViewHolder {
        val orderName: TextView
        val orderDate: TextView
        val orderPrice: TextView
        val orderImage: ImageView

        constructor(itemView: View) : super(itemView) {
            orderName = itemView.tvOrderName
            orderPrice = itemView.tvOrderPrice
            orderDate = itemView.tvOrderDate
            orderImage = itemView.imOrder


        }

    }
}