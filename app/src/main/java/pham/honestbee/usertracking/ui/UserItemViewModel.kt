package pham.honestbee.usertracking.ui

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import pham.honestbee.usertracking.vo.User

/**
 * Created by Pham on 23/1/2019.
 */
class UserItemViewModel(val user: User) : BaseObservable() {
    @Bindable
    fun getTitle(): String? {
        return if (!TextUtils.isEmpty(user.snippet?.title)) user.snippet?.title else ""
    }

    @Bindable
    fun getDefaultThumbnailUrl(): String? {
        return user.snippet?.thumbnails?.default?.url
    }

    @Bindable
    fun getMediumThumbnailUrl(): String? {
        return user.snippet?.thumbnails?.medium?.url
    }

    @Bindable
    fun getHighThumbnailUrl(): String? {
        return user.snippet?.thumbnails?.high?.url
    }

    @Bindable
    fun getUserId(): String? {
        return user.snippet?.resource?.videoId
    }
}