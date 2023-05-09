package am.a_t.furnituremarket.domain.iteractors

import am.a_t.furnituremarket.domain.entity.User
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE userName = :name AND user_password = :password")
    suspend fun getUser(name: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
}