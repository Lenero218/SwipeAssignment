package com.example.swipeassessment.Remote.model

import com.google.gson.annotations.SerializedName

data class PostProductDto(
    @SerializedName("message")
    val message : String,

    @SerializedName("product_details")
    val productDetails : ProductDetailsDto,

    @SerializedName("product_id")
    val productId : Int,

    @SerializedName("success")
    val success : Boolean

)
