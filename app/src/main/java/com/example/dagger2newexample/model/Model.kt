package com.example.dagger2newexample.model

data class Model (

    var pk: Int, // 583
    var title: String?, // Pizza Potato Skins
    var publisher: String?, // mitch
    var featured_image: String?, // https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png
    var rating: Int, // 16
    var source_url: String?, // http://thepioneerwoman.com/cooking/2013/04/pizza-potato-skins/
    var description: String?, // N/A
    var cooking_instructions: String?, // null
    var ingredients: List<String>?,
    var date_added: String?, // November 11 2020
    var date_updated: String? // November 11 2020

    )