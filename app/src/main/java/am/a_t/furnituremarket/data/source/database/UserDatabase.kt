package am.a_t.furnituremarket.data.source.database

import am.a_t.furnituremarket.data.source.convert.Converters
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.domain.entity.User
import am.a_t.furnituremarket.domain.iteractors.ProductDao
import am.a_t.furnituremarket.domain.iteractors.UserDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        User::class,
        Product::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}