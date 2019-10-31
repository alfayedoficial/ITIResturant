package com.tot.itiresturant.viewmodel

import android.app.Activity
import android.provider.ContactsContract
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tot.itiresturant.R
import com.tot.itiresturant.model.ChatMessage



class ChatViewModel(var activity: Activity) : ViewModel(){
    var repo: Repo = Repo(activity)
    var ownerMessage:ChatMessage = ChatMessage(activity.getString(R.string.app_name),activity.getString(R.string.owner_message))
    fun sendMessage(message: ChatMessage){
        repo.sendMessage(message)
        repo.sendMessage(ownerMessage)
    }

    fun getAllMessages(): LiveData<List<ChatMessage>> {
        return repo.getAllNotes()
    }
}