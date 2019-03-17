package pham.honestbee.usertracking.vo

import com.google.gson.annotations.SerializedName


/**
 * Created by Allen on 2019-01-22.
 */

class Response {
    @SerializedName("items")
    var users: List<User>? = null
}