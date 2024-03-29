package com.example.swipeassessment.Remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailsDto(
    @SerializedName("image")
    val image : String,

    @SerializedName("price")
    val price : Double,

    @SerializedName("product_name")
    val productName : String,

    @SerializedName("product_type")
    val productType : String,

    @SerializedName("tax")
    val tax : Double
) : Parcelable