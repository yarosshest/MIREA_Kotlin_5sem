package com.example.work4.modules

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.work4.model.AppDatabase
import com.example.work4.model.user.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(mApplication: Application) {
    private val appDatabase: AppDatabase

    init {
        appDatabase = databaseBuilder(mApplication, AppDatabase::class.java, "db").build()
    }
//    @Singleton
    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}