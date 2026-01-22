package com.vrsalex.matuleapp.domain.product

import com.vrsalex.matuleapp.domain.category.Category

data class Product(
    val id: Long,
    val title: String,
    val subtitle: String,
    val price: Int,
    val description: String,
    val weight: String,
    val category: Category?
)