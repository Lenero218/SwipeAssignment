package com.example.swipeassessment.Remote

import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Remote.model.PostProductDto
import com.example.swipeassessment.Remote.model.ProductDetailsDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApi {

    @GET("get")
    suspend fun fetchResults() : Response<List<ProductDetailsDto>>

    @POST("add")
    suspend fun postProductDetails(
        @Body productDto : PostProductDetails
    ) : Response<PostProductDto>

}