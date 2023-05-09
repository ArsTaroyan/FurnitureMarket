package am.a_t.furnituremarket.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("products")
data class Product(
    @PrimaryKey(autoGenerate = true) val productId: Long,
    @ColumnInfo("user_id") var user_id: Long,
    @ColumnInfo("product_name") var productName: String,
    @ColumnInfo("product_image") var productImg: String,
    @ColumnInfo("product_type") var productType: String,
    @ColumnInfo("product_price") var productPrice: String,
    @ColumnInfo("product_count") var productCount: Int,
    @ColumnInfo("product_color") var productColor: String,
    @ColumnInfo("product_size") var productSize: String,
    @ColumnInfo("product_Rating") var productRating: String,
    @ColumnInfo("product_is_save") var productIsSave: Boolean,
    @ColumnInfo("isClick") var isClick: Boolean,
    @ColumnInfo("product_is_shop") var productIsShop: Boolean,
    @ColumnInfo("type") var type: String
)
