package com.tot.itiresturant.model

import android.os.Parcel
import android.os.Parcelable
import com.tot.itiresturant.view.adapter.ChatAdapter
import java.util.*

const val OWNER_VIEW:Int = 1
const val CUSTOMER_VIEW:Int = 2
data class ChatMessage (var userName:String?, var userImage:String?="", var userMessage:String? = "", var viewType:Int = OWNER_VIEW)