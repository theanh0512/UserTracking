package pham.honestbee.usertracking.vo

import com.google.gson.annotations.SerializedName


/**
 * Created by Allen on 2019-01-22.
 */
data class Thumbnail(@SerializedName("default") var default: DefaultThumbnail? = null,
                     @SerializedName("high") var high: HighThumbnail? = null,
                     @SerializedName("medium") var medium: MediumThumbnail? = null)

data class DefaultThumbnail(@SerializedName("url") var url: String? = null)
data class MediumThumbnail(@SerializedName("url") var url: String? = null)
data class HighThumbnail(@SerializedName("url") var url: String? = null)