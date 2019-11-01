package com.tot.itiresturant.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tot.itiresturant.model.ChatMessage
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.viewmodel.SignInViewModel
import com.tot.itiresturant.viewmodel.SignUpViewModel

class Repository () {

    private var context: Context? = null
    private var signUpViewModel: SignUpViewModel? = null
    private var signInViewModel: SignInViewModel? = null
    private var mAuth: FirebaseAuth? = null
    private var database: DatabaseReference? = null
    private var mutableLiveData:MutableLiveData<ArrayList<Order>>? = null
    private var msgMutLiveData: MutableLiveData<List<ChatMessage>>? = null
    private var orderArrayList: ArrayList<Order>? = null
    private var messagesArrayList: ArrayList<ChatMessage>? = null

    constructor(context: Context):this(){
        this.context = context.applicationContext
        signInViewModel = SignInViewModel(context)
        signUpViewModel = SignUpViewModel(context)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        mutableLiveData = MutableLiveData()
        msgMutLiveData = MutableLiveData()
        orderArrayList = arrayListOf(Order())
        messagesArrayList = arrayListOf(ChatMessage("","","",0))
    }

    fun newUser(emailAddress:String, password:String){
        mAuth!!.createUserWithEmailAndPassword(emailAddress, password)
            .addOnSuccessListener {
                signUpViewModel!!.setMsg("signUp success")
            }
            .addOnFailureListener{
                signUpViewModel!!.setMsg("failed")
            }
    }

    fun loginUser(emailAddress:String, password:String){
        mAuth!!.signInWithEmailAndPassword(emailAddress, password)
            .addOnSuccessListener {
                signInViewModel!!.setMsg("signIn success")
            }
            .addOnFailureListener{
                signInViewModel!!.setMsg("failed")
            }
    }
    fun getAllOrders(): MutableLiveData<ArrayList<Order>> {
        FirebaseDatabase.getInstance().reference.child("Orders")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val order = dataSnapshot.getValue(Order::class.java)
                    orderArrayList!!.add(order!!)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println(""+databaseError.message)
                }
            })
        mutableLiveData!!.value=orderArrayList
        return mutableLiveData as MutableLiveData<ArrayList<Order>>
    }

    fun getAllOrdersOrderViewModel():List<Order>{
        val orders:MutableList<Order>? = null
        database!!.child("Orders")
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

    fun getAllMessages(): MutableLiveData<List<ChatMessage>> {
        database!!.child("Messages")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val message = dataSnapshot.getValue(ChatMessage::class.java)
                    messagesArrayList!!.add(message!!)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println(""+databaseError.message)
                }
            })
        msgMutLiveData!!.value=messagesArrayList
        return msgMutLiveData as MutableLiveData<List<ChatMessage>>
    }

    fun deleteOrders(){
        database!!.child("Orders").removeValue()
    }

    fun insertOrder(order:Order)
    {
        database!!.child("Orders").child(order.id).setValue(order)
    }

    fun deleteOrder(order:Order){
        database!!.child("Orders").child(order.id).removeValue()
    }

    fun updateOrder(order:Order){
        database!!.child("Orders").child(order.id).removeValue()
        database!!.child("Orders").child(order.id).setValue(order)
    }

    fun sendMessage(message: ChatMessage) {
        database!!.child("Messages").push().setValue(message)
    }

}