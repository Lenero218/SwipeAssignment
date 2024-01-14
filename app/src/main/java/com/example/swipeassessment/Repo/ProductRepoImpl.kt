package com.example.swipeassessment.Repo

import android.util.Log
import com.example.swipeassessment.Database.DataSource.ProductLocalDataSource
import com.example.swipeassessment.Domain.mapper.ProductDetailsMapper
import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.Remote.data.ProductRemoteDataSource
import com.example.swipeassessment.Remote.model.PostProductDto
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.lang.Exception

class ProductRepoImpl(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource,
    private val productDetailsMapper: ProductDetailsMapper
) : ProductRepo {


    override suspend fun getProductDetails(): Flow<List<ProductDetails>> {
        coroutineScope {
            try {
                val response = productRemoteDataSource.getProductDetails()
                if (response.isSuccessful) {
                    response.body()?.isNotEmpty().let {
                        productLocalDataSource.saveDataToDb(
                            response.body()?.map(productDetailsMapper::dtoToDomain)!!.toList()
                        )
                    }
                } else{ }
            }catch (e : Exception){
                Log.e("error",e.message,e)
            }

        }


        return productLocalDataSource.getDataFromDb()
    }

    override suspend fun searchProductDetails(searchedProd: String): List<ProductDetails> {
        return productLocalDataSource.searchProduct(searchedProd)
    }

    override suspend fun postProduct(params: PostProductDetails): Response<PostProductDto> {
        return productRemoteDataSource.postProductDetails(params)
    }

}
