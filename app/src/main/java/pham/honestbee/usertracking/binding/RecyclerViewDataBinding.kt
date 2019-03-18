package pham.honestbee.usertracking.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import pham.honestbee.usertracking.ui.UserAdapter
import pham.honestbee.usertracking.vo.User


/**
 * Created by Pham on 27/8/2018.
 */
class RecyclerViewDataBinding {
    /**
     * Binds the data to the [android.support.v7.widget.RecyclerView.Adapter], sets the
     * recycler view on the adapter, and performs some other recycler view initialization.
     *
     * @param recyclerView passed in automatically with the data binding
     * @param adapter      must be explicitly passed in
     * @param data         must be explicitly passed in
     */
    @BindingAdapter("app:adapter", "app:data")
    fun bind(recyclerView: RecyclerView?, adapter: UserAdapter?, data: List<User>?) {
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(
                DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        adapter?.updateData(data)
        val animation = TranslateAnimation(Animation.ABSOLUTE, //from xType
                0f,
                Animation.ABSOLUTE, //to xType
                0f,
                Animation.ABSOLUTE, //from yType
                500f,
                Animation.ABSOLUTE, //to yType
                0f)
        animation.duration = 2000
        recyclerView?.animation = animation
        animation.start()
    }
}