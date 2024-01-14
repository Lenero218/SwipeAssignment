package com.example.swipeassessment.Domain.usecase

interface UseCaseSuspend<Params, Return> {
    suspend operator fun invoke(params: Params): Return
}