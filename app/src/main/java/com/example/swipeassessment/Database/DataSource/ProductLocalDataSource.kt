package com.example.swipeassessment.Database.DataSource

import com.example.swipeassessment.Domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {

    suspend fun saveDataToDb(productDetails : List<ProductDetails>)

    suspend fun getDataFromDb() : Flow<List<ProductDetails>>

    suspend fun searchProduct(searchedProd : String) : List<ProductDetails>
}