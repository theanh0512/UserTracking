package pham.honestbee.usertracking.ui

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.*
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import pham.honestbee.usertracking.repository.UserRepository
import pham.honestbee.usertracking.vo.User
import javax.inject.Inject

/**
 * Created by Pham on 11/2/2018
 */
class MainViewModel @Inject constructor(val context: Context, val userRepository: UserRepository) : ViewModel(), Observable {
    val loading = ObservableBoolean(false)
    val loadSuccess = ObservableBoolean(false)
    val users = ObservableArrayList<User>()
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    private val compositeDisposable = CompositeDisposable()
    private val userAdapter = UserAdapter(object : UserAdapter.UserListener{
        override fun onUserClick(userId: String?) {
//            val intent = Intent(context, YoutubePlayerActivity::class.java)
//            intent.putExtra(YoutubePlayerActivity.KEY_VIDEO_ID_INTENT, videoId)
//            context.startActivity(intent)
        }
    })

    init {
        //loadUsers(WORK_PLAYLIST_ID)
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

    fun loadUsers(playlistId: String) {
        loading.set(true)
        compositeDisposable.add(userRepository.getUsers(playlistId)
                .subscribe({ response ->
                    loading.set(false)
                    loadSuccess.set(true)
                    users.addAll(response.users as ArrayList)
                }, { throwable ->
                    throwable.printStackTrace()
                    loading.set(false)
                    loadSuccess.set(false)
                })
                { Log.d("Users", "Completed") })
    }

    companion object {
    }
}
