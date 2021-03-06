package com.example.dagger2newexample.presentations.mainFragment

import com.example.dagger2newexample.presentations.mainFragment.FoodCategory.*

enum class FoodCategory(val value: String) {

    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut"),

}

fun getAllFoodCategorys(): List<FoodCategory> {
    return listOf(
        CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT
    )
}

fun getCategory(value: String): FoodCategory? {
    val map = FoodCategory.values().associateBy { FoodCategory::value }
    return map[value]
}
