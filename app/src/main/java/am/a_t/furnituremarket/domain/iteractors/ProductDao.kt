package am.a_t.furnituremarket.domain.iteractors

import am.a_t.furnituremarket.domain.entity.Product
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE user_id = :userId")
    fun getAllProduct(userId: Long): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE isClick = :isClick")
    suspend fun getProduct(isClick: Boolean): Product?

    @Update
    suspend fun updateUserProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)
}