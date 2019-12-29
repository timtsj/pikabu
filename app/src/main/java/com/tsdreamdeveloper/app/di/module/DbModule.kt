package com.tsdreamdeveloper.app.di.module

import android.content.Context
import androidx.room.Room
import com.tsdreamdeveloper.app.data.database.PikabuDatabase
import com.tsdreamdeveloper.app.di.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @PerApplication
    @Provides
    fun providePikabuDatabase(context: Context): PikabuDatabase =
            Room.databaseBuilder(context, PikabuDatabase::class.java, PikabuDatabase.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    @PerApplication
    @Provides
    fun providePostDao(db: PikabuDatabase) = db.getPostDao()
}