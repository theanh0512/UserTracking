package pham.honestbee.usertracking.data

import pham.honestbee.usertracking.vo.User

/**
 * Main entry point for accessing display Users data.
 */
interface UsersDataSource {

    fun getUsers(callback: LoadUsersCallback)

    fun saveUser(user: User)

    fun deleteAllUsers()

    fun refreshUsers()

    interface LoadUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable()
    }

    interface GetUserCallback {

        fun onUserLoaded(user: User)

        fun onDataNotAvailable()
    }
}
