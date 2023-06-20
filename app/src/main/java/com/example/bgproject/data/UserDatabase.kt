package com.example.bgproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bgproject.model.Response
import com.example.bgproject.model.Result
import com.example.bgproject.model.Tgl
import com.example.bgproject.model.User


@Database(
    entities = [
        User::class,
        Tgl::class,
        Response::class,
    ],
    version = 5, exportSchema = false
)
@TypeConverters(ResultTypeConverter::class)

abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private val migration1to2 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // perform database migration
            }
        }

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).addMigrations(migration1to2).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
