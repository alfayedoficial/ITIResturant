package com.tot.itiresturant.view.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tot.itiresturant.R
import com.tot.itiresturant.model.CUSTOMER_VIEW
import com.tot.itiresturant.model.ChatMessage
import com.tot.itiresturant.view.adapter.ChatAdapter
import com.tot.itiresturant.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private lateinit var adapter:ChatAdapter
    private lateinit var viewModel:ChatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        adapter = ChatAdapter(this)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        recyclerviewChat.layoutManager = layoutManager
        recyclerviewChat.adapter = adapter
        viewModel = ViewModelProviders.of(this,MyViewModelFactory(this)).get(ChatViewModel::class.java)
        btnSendChat.setOnClickListener {
            var message = ChatMessage(editTextChat.text.toString())
            message.viewType = CUSTOMER_VIEW
            viewModel.sendMessage(message)
            editTextChat.setText("")
        }

    }

    internal class MyViewModelFactory(private val mActivity: Activity) : ViewModelProvider.Factory {


        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChatViewModel(mActivity) as T
        }
    }
}
