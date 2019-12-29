package com.tsdreamdeveloper.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tsdreamdeveloper.app.data.database.converter.DateConverter
import com.tsdreamdeveloper.app.data.database.converter.HashMapConverter
import com.tsdreamdeveloper.app.data.database.converter.IntConverter
import com.tsdreamdeveloper.app.data.database.converter.StringConverter
import com.tsdreamdeveloper.app.data.database.dao.PostDao
import com.tsdreamdeveloper.app.data.database.model.PostDbModel
import com.tsdreamdeveloper.app.data.database.model.PostDetailDbModel

@Database(entities = [
    PostDbModel::class,
    PostDetailDbModel::class
],
        version = 1, exportSchema = false)
@TypeConverters(IntConverter::class, HashMapConverter::class, DateConverter::class, StringConverter::class)
abstract class PikabuDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "PikabuDatabase"
    }

    abstract fun getPostDao(): PostDao

    fun clear() {
        getPostDao().clear()
    }
}