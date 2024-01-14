package com.example.swipeassessment.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swipeassessment.Domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productDetails: List<ProductDetails>)

    //Using % for product_name contains operations and || for string concatenation
    @Query("Select * from ProductDetails where product_name like '%' || :productName || '%' ")
    fun searchProduct(productName : String) : List<ProductDetails>

    @Query("Select * from ProductDetails")
    fun searchProduct() : Flow<List<ProductDetails>>
}