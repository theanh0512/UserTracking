package pham.honestbee.usertracking.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import pham.honestbee.usertracking.R
import pham.honestbee.usertracking.databinding.ItemVideoYoutubeBinding
import pham.honestbee.usertracking.vo.User

/**
 * Created by Pham on 27/8/2018.
 */
class UserAdapter constructor(val listener: UserListener): RecyclerView.Adapter<UserAdapter.DataViewHolder>() {
    private val data: MutableList<User>

    init {
        this.data = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_video_youtube,
                FrameLayout(parent.context), false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataModel = data[position]
        holder.setViewModel(UserItemViewModel(dataModel))
        holder.binding?.itemView?.setOnClickListener {
            listener.onUserClick(data[position].snippet?.resource?.videoId)
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onViewAttachedToWindow(holder: DataViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: DataViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun updateData(data: List<User>?) {
        this.data.clear()
        this.data.addAll(data as ArrayList)
        notifyDataSetChanged()
    }

    // in case the view cannot be recycled due to transient state
    override fun onFailedToRecycleView(holder: DataViewHolder): Boolean {
        holder.itemView.clearAnimation()
        return super.onFailedToRecycleView(holder)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemVideoYoutubeBinding? = null

        init {
            bind()
        }

        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView)
            }
        }

        fun unbind() {
            if (binding != null) {
                binding!!.unbind() // Don't forget to unbind
            }
        }

        fun setViewModel(viewModel: UserItemViewModel) {
            if (binding != null) {
                binding!!.viewModel = viewModel
            }
        }
    }

    interface UserListener {
        fun onUserClick(userId: String?)
    }

    companion object {
        private val TAG = "DataAdapter"
    }
}