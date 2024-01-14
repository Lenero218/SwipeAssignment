package com.example.swipeassessment.Domain.mapper

import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.Remote.model.ProductDetailsDto
import com.example.swipeassessment.Mapper.Mapper

class ProductDetailsMapper() : Mapper<ProductDetails, ProductDetailsDto>() {
    override fun dtoToDomain(dto: ProductDetailsDto): ProductDetails = with(dto){
        ProductDetails(
            image = this.image,
            price = this.price,
            product_name = this.productName,
            product_type = this.productType,
            tax = this.tax
        )
    }

    override fun domainToDto(domain: ProductDetails): ProductDetailsDto = with(domain){
        ProductDetailsDto(
            image = this.image,
            price = this.price,
            productName = this.product_name,
            productType = this.product_type,
            tax = this.tax
        )
    }

}