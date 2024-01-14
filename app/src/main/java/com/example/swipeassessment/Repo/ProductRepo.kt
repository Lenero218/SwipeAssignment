package com.example.swipeassessment.Repo

import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.Remote.model.PostProductDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepo {

    suspend fun getProductDetails() : Flow<List<ProductDetails>>

    suspend fun searchProductDetails(searchedProd: String) : List<ProductDetails>

    suspend fun postProduct(params : PostProductDetails) : Response<PostProductDto>

}