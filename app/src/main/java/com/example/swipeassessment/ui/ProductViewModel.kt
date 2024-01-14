package com.example.swipeassessment.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.Domain.usecase.GetProductDetailsUseCase
import com.example.swipeassessment.Domain.usecase.PostProductDetailsUseCase
import com.example.swipeassessment.Domain.usecase.SearchProductUseCase
import com.example.swipeassessment.Remote.model.PostProductDto
import com.example.swipeassessment.Remote.model.ProductDetailsDto
import com.example.swipeassessment.Utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val searchProductUseCase: SearchProductUseCase,
    private val postProductUseCase: PostProductDetailsUseCase
) : ViewModel() {

    init {
        getProductDetails()
    }

    private val _productDetails: MutableStateFlow<Resource<List<ProductDetails>>> =
        MutableStateFlow(Resource.Success(listOf()))
    val productDetails = _productDetails.asStateFlow()

    private val _addProduct: MutableStateFlow<Resource<PostProductDto>> =
        MutableStateFlow(Resource.loading())
    val addProduct = _addProduct.asStateFlow()


    private fun getProductDetails() = viewModelScope.launch(Dispatchers.IO) {

        _productDetails.emit(Resource.loading())

        try {
            getProductDetailsUseCase.invoke(Unit).collectLatest { productDetails ->
                _productDetails.emit(Resource.Success(productDetails))
            }
        } catch (e: Exception) {
            e.message?.let { msg ->
                _productDetails.emit(Resource.Error(msg))
            }
        }


    }

    fun searchProduct(searchedProduct: String) = viewModelScope.launch(Dispatchers.IO) {

        _productDetails.emit(Resource.loading())

        _productDetails.emit(Resource.Success(searchProductUseCase(searchedProduct)))


    }

    fun postProduct(params: PostProductDetails) = viewModelScope.launch(Dispatchers.IO) {

        _productDetails.emit(Resource.loading())

        try {
            val response = postProductUseCase(params)
            if (response.isSuccessful) {
                _productDetails.emit(Resource.Success())
            } else {
                _productDetails.emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            e.message?.let { msg ->
                _productDetails.emit(Resource.Error(msg))
            }

        }
    }
}

