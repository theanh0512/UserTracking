package pham.honestbee.usertracking.data.source.local

import android.content.Context
import pham.honestbee.usertracking.AppExecutors
import pham.honestbee.usertracking.data.UsersDataSource
import pham.honestbee.usertracking.data.UsersDataSource.LoadUsersCallback
import pham.honestbee.usertracking.db.UserDao
import pham.honestbee.usertracking.vo.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Concrete implementation of a data source as a db.
 */
@Singleton
class UsersLocalDataSource @Inject constructor(val context: Context,
                                               val userDao: UserDao,
                                               val appExecutors: AppExecutors) : UsersDataSource {
    override fun refreshUsers() {
        // No need to handle here since UserRepository will be handling it
    }

    override fun getUsers(callback: LoadUsersCallback) {
        val runnable = Runnable {
            val users = userDao.getUsers()
            appExecutors.mainThread().execute {
                if (users.isEmpty()) {
                    // This will be called if the table is new or just empty.
                    callback.onDataNotAvailable()
                } else {
                    callback.onUsersLoaded(users)
                }
            }
        }

        appExecutors.diskIO().execute(runnable)
    }

    override fun saveUser(user: User) {
        checkNotNull(user)
        val saveRunnable = Runnable { userDao.insert(user) }
        appExecutors.diskIO().execute(saveRunnable)
    }

    override fun deleteAllUsers() {
        val deleteRunnable = Runnable { userDao.deleteUsers() }

        appExecutors.diskIO().execute(deleteRunnable)
    }
}
