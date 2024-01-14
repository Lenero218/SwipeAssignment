package com.example.swipeassessment.Domain.usecase

import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Remote.model.PostProductDto
import com.example.swipeassessment.Repo.ProductRepo
import retrofit2.Response

class PostProductDetailsUseCase(
    private val repo : ProductRepo,
) : UseCaseSuspend<PostProductDetails, Response<PostProductDto>> {
    override suspend fun invoke(params: PostProductDetails): Response<PostProductDto> {
        return repo.postProduct(params)
    }
}
