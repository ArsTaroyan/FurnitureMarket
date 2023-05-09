package am.a_t.furnituremarket.data.repo

import am.a_t.furnituremarket.data.preferences.Preference
import am.a_t.furnituremarket.data.preferences.Preference.Companion.USER_LOGIN
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.domain.entity.User
import am.a_t.furnituremarket.domain.iteractors.ProductDao
import am.a_t.furnituremarket.domain.iteractors.UserDao
import am.a_t.furnituremarket.extension.convertGsonToString
import am.a_t.furnituremarket.extension.convertStringToGson
import kotlinx.coroutines.flow.Flow

class Repository(private val userDao: UserDao, private val productDao: ProductDao, private val preference: Preference) {

    fun getAllProduct(id: Long): Flow<List<Product>> =
        productDao.getAllProduct(id)

    suspend fun getUser(name: String, password: String): User? {
        val user = userDao.getUser(name, password)
        if (user != null) {
            preference.saveUser(USER_LOGIN, user.convertGsonToString())
        }
        return user
    }

    suspend fun getProduct(isClick: Boolean): Product? = productDao.getProduct(isClick)

    suspend fun getLocal(): User? = preference.readUser(USER_LOGIN)?.convertStringToGson()

    suspend fun removeUser(): Boolean {
        preference.removeUser(USER_LOGIN)
        return preference.readUser(USER_LOGIN).isNullOrEmpty()
    }

    suspend fun updateUserProduct(product: Product) {
        productDao.updateUserProduct(product)
    }

    suspend fun addUser(user: User): Boolean {
        userDao.addUser(user)
        preference.saveUser(USER_LOGIN, getUser(user.userName, user.userPassword).convertGsonToString())
        return !preference.readUser(USER_LOGIN).isNullOrEmpty()
    }

    suspend fun auth(): Boolean = !preference.readUser(USER_LOGIN).isNullOrEmpty()

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }
}