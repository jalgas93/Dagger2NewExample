package com.example.dagger2newexample.network

import com.example.dagger2newexample.model.Model
import com.example.dagger2newexample.utils.Mapper

class RetrofitMapper:Mapper<RetrofitModel, Model> {
    override fun mapToDomainModel(entity: RetrofitModel): Model {
     return Model(
         pk = entity.pk,
         title = entity.title,
         publisher = entity.publisher,
         featured_image = entity.featuredImage,
         rating = entity.rating,
         source_url = entity.sourceUrl,
         cooking_instructions = entity.cookingInstructions,
         date_updated = entity.dateUpdated,
         date_added = entity.dateAdded,
         description = entity.description,
         ingredients = entity.ingredients
     )
    }

    override fun mapFromDomainModel(domainModel: Model): RetrofitModel {
   return RetrofitModel(
       pk = domainModel.pk,
       title = domainModel.title,
       publisher = domainModel.publisher,
       rating = domainModel.rating,
       description = domainModel.description,
       ingredients = domainModel.ingredients,
       sourceUrl = domainModel.source_url,
       dateUpdated = domainModel.date_updated,
       dateAdded = domainModel.date_added,
       cookingInstructions = domainModel.cooking_instructions,
       featuredImage = domainModel.featured_image
   )
    }

    fun mapToDomainModelList(entity:List<RetrofitModel>):List<Model>{
        return entity.map { mapToDomainModel(it) }
    }
}