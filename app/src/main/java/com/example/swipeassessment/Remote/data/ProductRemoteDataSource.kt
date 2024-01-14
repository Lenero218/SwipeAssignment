package com.example.swipeassessment.Remote.data

import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Remote.model.PostProductDto
import com.example.swipeassessment.Remote.model.ProductDetailsDto
import retrofit2.Response

interface ProductRemoteDataSource {

    suspend fun getProductDetails() : Response<List<ProductDetailsDto>>

    suspend fun postProductDetails(productDetailsDto: PostProductDetails) : Response<PostProductDto>
}