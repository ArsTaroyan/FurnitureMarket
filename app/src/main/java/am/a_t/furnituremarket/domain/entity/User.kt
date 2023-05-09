package am.a_t.furnituremarket.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index("userName", unique = true)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val userName: String,
    @ColumnInfo("user_password") val userPassword: String,
    @ColumnInfo("user_products") val userProducts: ArrayList<Product>
)