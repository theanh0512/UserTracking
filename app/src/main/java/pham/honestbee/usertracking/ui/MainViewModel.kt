package pham.honestbee.usertracking.ui

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.*
import android.databinding.Observable
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import pham.honestbee.usertracking.data.UsersDataSource
import pham.honestbee.usertracking.data.source.repository.UserRepository
import pham.honestbee.usertracking.vo.User
import java.util.*
import javax.inject.Inject

/**
 * Created by Pham on 11/2/2018
 */
class MainViewModel @Inject constructor(val context: Context, val userRepository: UserRepository) : ViewModel(), Observable {
    val loading = ObservableBoolean(false)
    val loadSuccess = ObservableBoolean(false)
    val displayUsers = ObservableArrayList<User>()
    val selectedUser = ObservableField<User>()
    var mapLink = ObservableField<String>(DEFAULT_MAP_LINK)
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    private val compositeDisposable = CompositeDisposable()
    private val userAdapter = UserAdapter(object : UserAdapter.UserListener {
        override fun onUserClick(user: User?) {
            selectedUser.set(user)
            val selectedLat = selectedUser.get()?.address?.geo?.lat
            val selectedLng = selectedUser.get()?.address?.geo?.lng
            if (selectedUser.get() != null) {
                mapLink.set("https://maps.googleapis.com/maps/api/staticmap?zoom=4&scale=2&size=800x400&maptype=terrain&markers=color:blue%7Clabel:U%7C$selectedLat%2c%20$selectedLng&style=feature:road.highway%7Celement:geometry%7Cvisibility:simplified%7Ccolor:0xc280e9&style=feature:transit.line%7Cvisibility:simplified%7Ccolor:0xbababa&style=feature:road.highway%7Celement:labels.text.stroke%7Cvisibility:on%7Ccolor:0xb06eba&style=feature:road.highway%7Celement:labels.text.fill%7Cvisibility:on%7Ccolor:0xffffff&key=AIzaSyDIJ9XX2ZvRKCJcFRrl-lRanEtFUow4piM")
            }
            Log.d("click", mapLink.get())
        }
    })

    init {
        loadUsers()
    }

    // we use these here because the databinding lib is still having a timing bug
    // where calling notifyChanged() or notifyPropertyChanged(int) results in no action taking place
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    @Bindable
    fun getAdapter(): UserAdapter {
        return this.userAdapter
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loadUsers() {
        loading.set(true)
        userRepository.getUsers(object : UsersDataSource.LoadUsersCallback {
            override fun onUsersLoaded(users: List<User>) {
                val usersToShow = ArrayList<User>()
                for (user in users) {
                    usersToShow.add(user)
                }
                loading.set(false)
                loadSuccess.set(true)
                displayUsers.clear()
                displayUsers.addAll(usersToShow)
            }

            override fun onDataNotAvailable() {
                loading.set(false)
                loadSuccess.set(false)
            }
        })
    }

    companion object {
        const val DEFAULT_MAP_LINK = "https://maps.googleapis.com/maps/api/staticmap?center=Singapore&zoom=11&scale=2&size=800x400&maptype=terrain&key=AIzaSyDIJ9XX2ZvRKCJcFRrl-lRanEtFUow4piM";
    }
}
