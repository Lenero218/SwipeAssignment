package com.example.swipeassessment.Database.DataSource

import com.example.swipeassessment.Database.ProductDao
import com.example.swipeassessment.Domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow

class ProductLocalDataSourceImpl(
    private val productDao: ProductDao
) : ProductLocalDataSource {
    override suspend fun saveDataToDb(productDetails: List<ProductDetails>) {
        productDao.insert(productDetails)
    }

    override suspend fun searchProduct(searchedProd: String): List<ProductDetails> {
        return productDao.searchProduct(searchedProd)    }

    override suspend fun getDataFromDb(): Flow<List<ProductDetails>> {
        return productDao.searchProduct()
    }
}