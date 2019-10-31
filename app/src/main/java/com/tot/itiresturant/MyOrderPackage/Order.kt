package com.tot.itiresturant.MyOrderPackage

import android.os.Parcel
import android.os.Parcelable

class Order() :Parcelable {
    var name: String
        get() {
            return name
        }
        set(name: String) {
            this.name = name
        }
    var price: Double
        get() {
            return price
        }
        set(price: Double) {
            this.price = price
        }

    var date: String
        get() {
            return date
        }
        set(date: String) {
            this.date = date
        }
    var number: Int
        get() {
            return number
        }
        set(number: Int) {
            this.number = number
        }
    var image: String
        get() {
            return image
        }
        set(image: String) {
            this.image = image
        }
    var description: String
        get() {
            return description
        }
        set(description: String) {
            this.description = description
        }
    var totalPrice: Double
        get() {
            return totalPrice
        }
        set(totalPrice: Double) {
            this.totalPrice = totalPrice
        }

    constructor(parcel: Parcel) : this() {
    }

    constructor(
        name: String,
        price: Double,
        date: String,
        number: Int,
        image: String,
        description: String,
        totalPrice: Double
    ) : this() {
        this.name = name
        this.price = price
        this.date = date
        this.number = number
        this.image = image
        this.description = description
        this.totalPrice = totalPrice
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}