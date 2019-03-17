package pham.honestbee.usertracking.api

import io.reactivex.Observable
import pham.honestbee.usertracking.vo.Response
import pham.honestbee.usertracking.vo.User
import retrofit2.http.GET

/**
 * Created by Pham on 26/8/2018.
 */
interface UserService {
    @GET("users")
    fun getUsers(): Observable<List<User>>
}