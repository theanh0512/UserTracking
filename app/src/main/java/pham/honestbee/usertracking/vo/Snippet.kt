package pham.honestbee.usertracking.vo

import com.google.gson.annotations.SerializedName


/**
 * Created by Allen on 2019-01-22.
 */
data class Snippet(@SerializedName("title") var title: String? = null,
                   @SerializedName("thumbnails") var thumbnails: Thumbnail? = null,
                   @SerializedName("resourceId") var resource: Resource? = null)

data class Resource(@SerializedName("videoId") var videoId: String? = null)