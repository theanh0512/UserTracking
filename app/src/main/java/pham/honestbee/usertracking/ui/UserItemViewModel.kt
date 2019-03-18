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
    fun getName(): String? {
        return if (!TextUtils.isEmpty(user.name)) user.name else ""
    }

    @Bindable
    fun getEmail(): String? {
        return if (!TextUtils.isEmpty(user.email)) user.email else ""
    }

    @Bindable
    fun getRandomAvatar(): String? {
        return "https://robohash.org/${user.id}"
    }
}