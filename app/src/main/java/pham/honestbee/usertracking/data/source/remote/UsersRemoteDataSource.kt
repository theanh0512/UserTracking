package pham.honestbee.usertracking.data.source.remote

import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pham.honestbee.usertracking.api.UserService
import pham.honestbee.usertracking.data.UsersDataSource
import pham.honestbee.usertracking.vo.User
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class UsersRemoteDataSource @Inject constructor(val context: Context,
                                                val userService: UserService) : UsersDataSource {
    override fun refreshUsers() {
        // No need to handle here since UserRepository will be handling it
    }

    private val compositeDisposable = CompositeDisposable()
    override fun getUsers(callback: UsersDataSource.LoadUsersCallback) {
        compositeDisposable.add(userService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    response.forEach { user -> USERS_SERVICE_DATA[user.id] = user }
                    callback.onUsersLoaded(ArrayList(USERS_SERVICE_DATA.values))
                }, { throwable ->
                    throwable.printStackTrace()
                })
                { Log.d("Users", "Completed") })
    }

    override fun saveUser(user: User) {
        USERS_SERVICE_DATA[user.id] = user
    }

    override fun deleteAllUsers() {
        USERS_SERVICE_DATA.clear()
    }

    companion object {

        private val USERS_SERVICE_DATA: MutableMap<Int, User>

        init {
            USERS_SERVICE_DATA = LinkedHashMap(2)
        }
    }
}
