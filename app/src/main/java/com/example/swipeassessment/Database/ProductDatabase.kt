package com.example.swipeassessment.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swipeassessment.Domain.model.ProductDetails
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ProductDetails::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao() : ProductDao

    companion object {

        @Volatile
        private var INSTANCE: ProductDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "user_data_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}