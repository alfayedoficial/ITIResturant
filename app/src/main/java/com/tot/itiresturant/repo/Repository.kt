package com.tot.itiresturant.repo

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firestore.v1.StructuredQuery
import com.tot.itiresturant.R
import com.tot.itiresturant.model.ChatMessage
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.view.activity.SignUpActivity
import com.tot.itiresturant.viewmodel.SignInViewModel
import com.tot.itiresturant.viewmodel.SignUpViewModel

class Repository () {
    private lateinit var application: Application
    constructor(application: Application):this(){
        this.application = application
    }
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private  var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var signUpViewModel: SignUpViewModel = SignUpViewModel(application)
    private var signInViewModel: SignInViewModel = SignInViewModel(application)
    private var mutableLiveData:MutableLiveData<ArrayList<Order>> = MutableLiveData()
    private var msgMutLiveData: MutableLiveData<List<ChatMessage>> = MutableLiveData()
    private var orderArrayList: ArrayList<Order> = arrayListOf(Order())
    private var notesArrayList: ArrayList<ChatMessage> = arrayListOf(ChatMessage("","","",0))

    fun newUser(emailAddress:String, password:String){
        mAuth.createUserWithEmailAndPassword(emailAddress, password)
            .addOnSuccessListener {
                signUpViewModel.setMsg("signUp success")
            }
            .addOnFailureListener{
                signUpViewModel.setMsg("failed")
            }
    }

    fun loginUser(emailAddress:String, password:String){
        mAuth.signInWithEmailAndPassword(emailAddress, password)
            .addOnSuccessListener {
                signInViewModel.setMsg("signIn success")
            }
            .addOnFailureListener{
                signInViewModel.setMsg("failed")
            }
    }
    fun getAllOrders(): MutableLiveData<ArrayList<Order>> {
        FirebaseDatabase.getInstance().reference.child("Orders")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val order = dataSnapshot.getValue(Order::class.java)
                    orderArrayList.add(order!!)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println(""+databaseError.message)
                }
            })
        mutableLiveData.value=orderArrayList
        return mutableLiveData
    }

    fun getAllOrdersOrderViewModel():List<Order>{
        val orders:MutableList<Order>? = null
        database.child("Orders")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (order:DataSnapshot in dataSnapshot.children) {
                        val orderValue = dataSnapshot.getValue(Order::class.java)
                        if(orderValue != null)
                            orders!!.add(orderValue)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println(""+databaseError.message)
                }
            })
        return orders as List<Order>
    }

    fun getAllNotes(): MutableLiveData<List<ChatMessage>> {
        database.child("Notes")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val note = dataSnapshot.getValue(ChatMessage::class.java)
                    notesArrayList.add(note!!)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println(""+databaseError.message)
                }
            })
        msgMutLiveData.value=notesArrayList
        return msgMutLiveData
    }

    fun deleteOrders(){
        database.child("Orders").removeValue()
    }

    fun insertOrder(order:Order)
    {
        database.child("Orders").child(order.id).setValue(order)
    }

    fun deleteOrder(order:Order){
        database.child("Orders").child(order.id).removeValue()
    }

    fun updateOrder(order:Order){
        database.child("Orders").child(order.id).removeValue()
        database.child("Orders").child(order.id).setValue(order)
    }

    fun sendMessage(message: ChatMessage) {

    }

}