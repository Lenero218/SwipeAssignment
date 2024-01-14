package com.example.swipeassessment.Remote.data

import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Remote.model.PostProductDto
import com.example.swipeassessment.Remote.model.ProductDetailsDto
import com.example.swipeassessment.Remote.ProductApi
import retrofit2.Response

class ProductRemoteDataSourceImpl(
    private val productApi: ProductApi
) : ProductRemoteDataSource {
    override suspend fun getProductDetails(): Response<List<ProductDetailsDto>> {
        return productApi.fetchResults()
    }

    override suspend fun postProductDetails(productDetailsDto: PostProductDetails): Response<PostProductDto> {
        return productApi.postProductDetails(productDetailsDto)
    }
}