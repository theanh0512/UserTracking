package pham.honestbee.usertracking.data.source.repository

import android.content.Context
import pham.honestbee.usertracking.data.UsersDataSource
import pham.honestbee.usertracking.data.source.Local
import pham.honestbee.usertracking.data.source.Remote
import pham.honestbee.usertracking.vo.User
import java.util.LinkedHashMap
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.collections.set

/**
 * Created by Pham on 25/8/2018.
 */
@Singleton
class UserRepository
@Inject constructor(val context: Context,
                    @Remote val userRemoteDataSource: UsersDataSource,
                    @Local val userLocalDataSource: UsersDataSource) : UsersDataSource {
    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    var cachedUsers: MutableMap<Int, User>? = null
    var cacheIsDirty = false

    override fun getUsers(callback: UsersDataSource.LoadUsersCallback) {
        checkNotNull(callback)

        // Respond immediately with cache if available and not dirty
        if (cachedUsers != null && !cacheIsDirty) {
            callback.onUsersLoaded(ArrayList(cachedUsers!!.values))
            return
        }

        if (cacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getUsersFromRemoteDataSource(callback)
        } else {
            // Query the local storage if available. If not, query the network.
            userLocalDataSource.getUsers(object : UsersDataSource.LoadUsersCallback {
                override fun onUsersLoaded(users: List<User>) {
                    refreshCache(users)
                    callback.onUsersLoaded(ArrayList(cachedUsers!!.values))
                }

                override fun onDataNotAvailable() {
                    getUsersFromRemoteDataSource(callback)
                }
            })
        }
    }

    private fun getUsersFromRemoteDataSource(callback: UsersDataSource.LoadUsersCallback) {
        userRemoteDataSource.getUsers(object : UsersDataSource.LoadUsersCallback {
            override fun onUsersLoaded(users: List<User>) {
                refreshCache(users)
                refreshLocalDataSource(users)
                callback.onUsersLoaded(ArrayList(cachedUsers!!.values))
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    private fun refreshCache(users: List<User>) {
        if (cachedUsers == null) {
            cachedUsers = LinkedHashMap()
        }
        cachedUsers!!.clear()
        for (user in users) {
            cachedUsers!![user.id] = user
        }
        cacheIsDirty = false
    }

    private fun refreshLocalDataSource(users: List<User>) {
        userLocalDataSource.deleteAllUsers()
        for (user in users) {
            userLocalDataSource.saveUser(user)
        }
    }

    override fun saveUser(user: User) {
        checkNotNull(user)
        userRemoteDataSource.saveUser(user)
        userLocalDataSource.saveUser(user)

        // Do in memory cache update to keep the app UI up to date
        if (cachedUsers == null) {
            cachedUsers = LinkedHashMap()
        }
        cachedUsers!![user.id] = user
    }

    override fun deleteAllUsers() {
        userRemoteDataSource.deleteAllUsers()
        userLocalDataSource.deleteAllUsers()

        if (cachedUsers == null) {
            cachedUsers = LinkedHashMap()
        }
        cachedUsers!!.clear()
    }

    override fun refreshUsers() {
        cacheIsDirty = true
    }

    companion object {
        private val TAG = "User"
    }
}