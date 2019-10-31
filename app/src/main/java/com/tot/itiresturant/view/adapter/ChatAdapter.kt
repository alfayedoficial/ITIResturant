package com.tot.itiresturant.view.adapter

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tot.itiresturant.R
import com.tot.itiresturant.model.ChatMessage
import kotlinx.android.synthetic.main.itemchat.view.*


class ChatAdapter(var context: Context) : RecyclerView.Adapter<ChatAdapter.MainViewHolder>() {

    val OWNER_VIEW:Int = 1
    val CUSTOMER_VIEW:Int = 2
    private var list:List<ChatMessage> = ArrayList<ChatMessage>()

    lateinit var messagesList:List<ChatMessage>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.itemchat,parent,false)
        view.setBackgroundColor(if(viewType == OWNER_VIEW){R.color.color1}else{R.color.circle_select})
        return MainViewHolder(view)    }

    override fun getItemCount(): Int {
        return if(messagesList.isEmpty()){0}else{messagesList.size}
    }

    override fun getItemViewType(position: Int): Int {
        return messagesList[position].viewType
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        if(messagesList[position].viewType == CUSTOMER_VIEW){
            holder.message.text = messagesList[position].userName
            Glide.with(context)
                .load(messagesList[position].userImage) //3
                .into(holder.userImage) //8
            holder.userImage.visibility = View.VISIBLE
            holder.ownerImage.visibility = View.GONE
        }
        else if(messagesList[position].viewType == OWNER_VIEW){
            holder.message.text = messagesList[position].userName
            holder.ownerImage.visibility = View.VISIBLE
            holder.userImage.visibility = View.GONE
        }
    }

    fun setAdapter(list: List<ChatMessage>){
        this.list = list
        notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userImage:ImageView = itemView.circularImgSender
        var message = itemView.txReceiverMessage
        var ownerImage:ImageView = itemView.circularImgReceiver
    }

}