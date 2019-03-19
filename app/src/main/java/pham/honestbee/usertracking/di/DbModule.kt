package pham.honestbee.usertracking.di

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import pham.honestbee.usertracking.db.UserDao
import pham.honestbee.usertracking.db.UserTrackingDb
import javax.inject.Singleton

/**
 * Created by Pham on 26/8/2018.
 */
@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): UserTrackingDb {
        return Room
                .databaseBuilder(app, UserTrackingDb::class.java, "usertracking.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: UserTrackingDb): UserDao {
        return db.userDao()
    }
}