package com.aghasemi.billing.data.local.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aghasemi.billing.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Category::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(application: Application): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(application, AppDatabase::class.java, "app.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            insertCategoryAtFirstTime(application)
                        }
                    })
                    .build()
                INSTANCE = instance
                return instance
            }
        }


        /*fun getInstance(app: Application): AppDatabase {
            synchronized(AppDatabase::class) {
                return Room.databaseBuilder(app, AppDatabase::class.java, "app.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //insertCategoryAtFirstTime(app)
                        }
                    })
                    .build()
            }
        }*/

        private fun insertCategoryAtFirstTime(app: Application) {
            CoroutineScope(Dispatchers.IO).launch {
                getInstance(app).categoryDao().insertAll(readInitialCategories(app))
            }
        }

        private fun readInitialCategories(app: Application): List<Category> {
            //read from assets
            return try {
                setOf(
                    Category(0,  "Income",  0,  Category.Type.Income),
                    Category(0,  "Outcome",  0,  Category.Type.Outcome),
                ).toList()
                /*val jsonFileString = getJsonFromAssets(app.applicationContext,"categories.json")
                val gson = Gson()
                val listType: Type = object : TypeToken<List<Category>>() {}.type

                gson.fromJson(jsonFileString, listType)*/
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}