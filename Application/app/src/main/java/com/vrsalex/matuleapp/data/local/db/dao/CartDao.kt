package com.vrsalex.matuleapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vrsalex.matuleapp.data.local.db.entity.CartItemEntity
import com.vrsalex.matuleapp.data.local.db.relation.CartItemWithProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Transaction
    @Query("SELECT * FROM cart")
    fun getCartFlow(): Flow<List<CartItemWithProductEntity>>

    @Query("SELECT * FROM cart WHERE productId = :id")
    suspend fun getCartItemByProductId(id: Long): CartItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItems(items: List<CartItemEntity>)

    @Query("DELETE FROM cart WHERE productId = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE cart SET quantity = quantity + 1 WHERE productId = :id")
    suspend fun incrementQuantity(id: Long)

    @Query("UPDATE cart SET quantity = quantity - 1 WHERE productId = :id AND quantity > 1")
    suspend fun decrementQuantity(id: Long)

    @Query("DELETE FROM cart")
    suspend fun clearCart()

}
