package com.tot.itiresturant.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order


class CartAdapter(var context: Context) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var ordersList: List<Order>? = null

    init {
        ordersList = ArrayList<Order>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        context = parent.getContext()
        val view = LayoutInflater.from(context).inflate(R.layout.myorder_row, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        holder.getTextViewName()!!.setText(ordersList!![position].name)
        holder.getTextViewDate()!!.setText(ordersList!![position].date)
        holder.getTextViewPrice()!!.setText("" + ordersList!![position].price)
        Glide.with(context!!).load(ordersList!![position].image).into(holder.getImageViewOrder()!!)

    }

    override fun getItemCount(): Int {
        return if (ordersList != null) ordersList!!.size else 0
    }

    fun setData(ordersList: List<Order>) {
        this.ordersList = ordersList
        notifyDataSetChanged()
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvOrderName: TextView? = null
        internal var tvOrderPrice: TextView? = null
        internal var tvOrderDate: TextView? = null
        internal var imOrder: ImageView? = null


        fun getTextViewName(): TextView? {
            if (tvOrderName == null) {
                tvOrderName = itemView.findViewById(R.id.tvOrderName)
            }
            return tvOrderName
        }

        fun getTextViewPrice(): TextView? {
            if (tvOrderPrice == null) {
                tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice)
            }
            return tvOrderPrice
        }

        fun getTextViewDate(): TextView? {
            if (tvOrderDate == null) {
                tvOrderDate = itemView.findViewById(R.id.tvOrderDate)
            }
            return tvOrderDate
        }


        fun getImageViewOrder(): ImageView? {
            if (imOrder == null) {
                imOrder = itemView.findViewById(R.id.imOrder)
            }
            return imOrder
        }
    }


}