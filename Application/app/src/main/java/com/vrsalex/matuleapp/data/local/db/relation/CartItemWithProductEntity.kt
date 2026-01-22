package com.vrsalex.matuleapp.data.local.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.vrsalex.matuleapp.data.local.db.entity.CartItemEntity
import com.vrsalex.matuleapp.data.local.db.entity.ProductEntity
import com.vrsalex.matuleapp.domain.cart.CartItem


data class CartItemWithProductEntity(
    @Embedded
    val cartItem: CartItemEntity,

    @Relation(
        entity = ProductEntity::class,
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: ProductWithCategoryEntity?
)
