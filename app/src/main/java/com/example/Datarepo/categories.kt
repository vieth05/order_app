package com.example.Datarepo

import com.example.Data.data_grid
import com.example.order_app.R

object categories {
    val categories = mutableListOf(
        data_grid(R.drawable.beverages, "Beverages"),
        data_grid(R.drawable.bread_bakery, "Bread & Bakery"),
        data_grid(R.drawable.vegetables, "Vegetables"),
        data_grid(R.drawable.fruit, "Fruit"),
        data_grid(R.drawable.egg, "Egg"),
        data_grid(R.drawable.frozen_veg, "Frozen Veg"),
        data_grid(R.drawable.home_care, "Homecare"),
        data_grid(R.drawable.pet_care, "Pet Care")
    )
}