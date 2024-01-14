package com.example.swipeassessment.Mapper

abstract class Mapper<Domain, Dto> {

    abstract fun dtoToDomain(dto: Dto) : Domain

    abstract fun domainToDto(domain: Domain) : Dto

    fun dtoToDomainList(dtoList: List<Dto>?): List<Domain> = try {
        (dtoList ?: emptyList()).map(::dtoToDomain)
    } catch (e: Exception) {
        emptyList()
    }

    fun domainToDtoList(domainList: List<Domain>?): List<Dto> = (domainList?: emptyList()).map(::domainToDto)

}