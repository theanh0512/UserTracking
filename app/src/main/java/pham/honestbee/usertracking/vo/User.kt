package pham.honestbee.usertracking.vo

import com.google.gson.annotations.SerializedName


/**
 * Created by Allen on 2019-01-22.
 */
data class User(@SerializedName("snippet") var snippet: Snippet? = null)