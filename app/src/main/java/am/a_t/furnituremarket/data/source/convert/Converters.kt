package am.a_t.furnituremarket.data.source.convert

import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.extension.convertGsonToString
import am.a_t.furnituremarket.extension.convertStringToGson
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: ArrayList<Product>): String = list.convertGsonToString()

    @TypeConverter
    fun toList(str: String): ArrayList<Product> = str.convertStringToGson()
}