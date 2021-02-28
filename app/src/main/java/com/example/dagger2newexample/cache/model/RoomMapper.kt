package com.example.dagger2newexample.cache.model

import com.example.dagger2newexample.model.Model
import com.example.dagger2newexample.utils.Mapper
import java.lang.StringBuilder

class RoomMapper : Mapper<RoomModel, Model> {
    override fun mapToDomainModel(entity: RoomModel): Model {
        return Model(
            pk = entity.id,
            title = entity.title,
            publisher = entity.publisher,
            featured_image = entity.featuredImage,
            rating = entity.rating,
            source_url = entity.sourceUrl,
            description = entity.description,
            cooking_instructions = entity.cookingInstructions,
            ingredients = toStingIngridient(entity.ingredients),
            date_added = entity.dateAdded,
            date_updated = entity.dateUpdated
        )
    }

    override fun mapFromDomainModel(domainModel: Model): RoomModel {
        return RoomModel(
            id = domainModel.pk,
            title = domainModel.title,
            publisher = domainModel.publisher,
            featuredImage = domainModel.featured_image,
            rating = domainModel.rating,
            sourceUrl = domainModel.source_url,
            description = domainModel.description,
            cookingInstructions = domainModel.cooking_instructions,
            ingredients = toListStringIngridient(domainModel.ingredients.orEmpty()),
            dateAdded = domainModel.date_added,
            dateUpdated = domainModel.date_updated
        )
    }

    private fun toStingIngridient(ingredientsString: String?): List<String> {
        val list: ArrayList<String> = ArrayList()
        ingredientsString?.let {
            for (ingredient in it.split(",")) {
                list.add(ingredient)
            }

        }
        return list

    }

    private fun toListStringIngridient(ingredients: List<String>): String {
        val ingredientString: StringBuilder = StringBuilder()
        for (ingredient in ingredients) {
            ingredientString.append("$ingredient,")
        }
        return ingredientString.toString()
    }


    fun mapToDomainModelToList(initial: List<RoomModel>): List<Model> {
        return initial.map {
            mapToDomainModel(it)
        }
    }

    fun mapFromDomainModelToList(initial:List<Model>):List<RoomModel>{
        return initial.map {
            mapFromDomainModel(it)
        }
    }

}