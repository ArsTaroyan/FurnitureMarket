package am.a_t.furnituremarket.di

import am.a_t.furnituremarket.data.repo.Repository
import am.a_t.furnituremarket.data.source.database.UserDatabase
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        Repository(getDatabase(androidContext()).userDao(), getDatabase(androidContext()).productDao(), get())
    }
}

@Volatile
private var INSTANCE: UserDatabase? = null
fun getDatabase(context: Context): UserDatabase {
    val tempInstance = INSTANCE
    if (tempInstance != null) {
        return tempInstance
    }
    synchronized(context.applicationContext) {
        val instance = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).build()
        INSTANCE = instance
        return instance
    }
}