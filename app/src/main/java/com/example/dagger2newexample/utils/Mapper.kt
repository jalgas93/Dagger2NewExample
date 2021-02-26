package com.example.dagger2newexample.utils

interface Mapper<Entity,DomainModel> {
    fun mapToDomainModel(entity: Entity):DomainModel
    fun mapFromDomainModel(domainModel: DomainModel):Entity
}