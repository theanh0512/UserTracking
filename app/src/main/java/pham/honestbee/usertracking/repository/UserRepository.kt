package pham.honestbee.usertracking.repository

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pham.honestbee.usertracking.api.UserService
import pham.honestbee.usertracking.vo.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Pham on 25/8/2018.
 */
@Singleton
class UserRepository @Inject constructor(val context: Context, val userService: UserService) {
    fun getUsers(): Observable<List<User>> {
        return userService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    companion object {
        private val TAG = "User"
    }
}