package com.tot.itiresturant.viewmodel

import android.app.Activity
import android.provider.ContactsContract
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.core.Repo

import com.tot.itiresturant.R
import com.tot.itiresturant.model.ChatMessage
import com.tot.itiresturant.repo.Repository


class ChatViewModel(var activity: Activity) : ViewModel(){
    var repository: Repository = Repository(activity.application)
    var ownerMessage:ChatMessage = ChatMessage(activity.getString(R.string.app_name),activity.getString(R.string.owner_message))
    fun sendMessage(message: ChatMessage){
        repository.sendMessage(message)
        repository.sendMessage(ownerMessage)
    }

    fun getAllMessages(): MutableLiveData<List<ChatMessage>> {
        return repository.getAllNotes()
    }
}