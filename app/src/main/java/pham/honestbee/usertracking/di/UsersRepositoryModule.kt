package pham.honestbee.usertracking.di

import dagger.Binds
import dagger.Module
import pham.honestbee.usertracking.data.UsersDataSource
import pham.honestbee.usertracking.data.source.Local
import pham.honestbee.usertracking.data.source.Remote
import pham.honestbee.usertracking.data.source.local.UsersLocalDataSource
import pham.honestbee.usertracking.data.source.remote.UsersRemoteDataSource
import javax.inject.Singleton

/**
 * This is used by Dagger to inject the required arguments into the
 * [pham.honestbee.usertracking.data.source.repository.UserRepository]
 */
@Module
abstract class UsersRepositoryModule {

    @Singleton
    @Binds
    @Local
    internal abstract fun provideUsersLocalDataSource(dataSource: UsersLocalDataSource): UsersDataSource

    @Singleton
    @Binds
    @Remote
    internal abstract fun provideUsersRemoteDataSource(dataSource: UsersRemoteDataSource): UsersDataSource
}
