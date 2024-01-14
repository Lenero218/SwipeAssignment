package com.example.swipeassessment.Domain.usecase

import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.Repo.ProductRepo
import kotlinx.coroutines.flow.Flow

class GetProductDetailsUseCase(
    private val repo : ProductRepo,
) : UseCaseSuspend<Unit, Flow<List<ProductDetails>>> {
    override suspend fun invoke(params: Unit): Flow<List<ProductDetails>> {
        return repo.getProductDetails()
    }

}