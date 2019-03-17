package pham.honestbee.usertracking.api

import io.reactivex.Observable
import pham.honestbee.usertracking.vo.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pham on 26/8/2018.
 */
interface UserService {
    @GET("playlistItems")
    fun getUsers(
            @Query("key") key: String,
            @Query("playlistId") playlistId: String,
            @Query("part") part: String,
            @Query("maxResults") maxResults: Int
    ): Observable<Response>
}