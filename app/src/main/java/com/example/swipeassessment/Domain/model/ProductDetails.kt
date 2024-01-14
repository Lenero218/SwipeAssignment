package com.example.swipeassessment.Domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ProductDetails", primaryKeys = ["image","product_name","product_type"])
data class ProductDetails(

    val image : String,
    val price : Double,
    val product_name : String,
    val product_type : String,
    val tax : Double
) : Parcelable