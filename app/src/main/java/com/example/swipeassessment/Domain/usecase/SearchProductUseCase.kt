package com.example.swipeassessment.Domain.usecase

import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.Repo.ProductRepo

class SearchProductUseCase(
    private val repo : ProductRepo,
) : UseCaseSuspend<String, List<ProductDetails>>{
    override suspend fun invoke(params: String): List<ProductDetails> {
        return repo.searchProductDetails(params)
    }

}