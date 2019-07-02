package com.chintansoni.buddies.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chintansoni.buddies.R
import com.chintansoni.buddies.databinding.ItemLoadingBinding
import com.chintansoni.buddies.databinding.ItemUserBinding
import com.chintansoni.buddies.model.local.entity.User
import com.chintansoni.buddies.model.remote.response.Dob
import com.chintansoni.buddies.model.remote.response.Location
import com.chintansoni.buddies.model.remote.response.Name
import com.chintansoni.buddies.model.remote.response.Picture
import com.chintansoni.buddies.util.UserDiffUtil
import com.chintansoni.buddies.view.viewholder.LoaderViewHolder
import com.chintansoni.buddies.view.viewholder.UserViewHolder

class UserRecyclerAdapter(context: Context) :
    ListAdapter<User, RecyclerView.ViewHolder>(UserDiffUtil()) {

    private val ITEM_TYPE_NORMAL = 1
    private val ITEM_TYPE_LOADER = 2
    private var listener: ItemTouchListener

    init {
        listener = context as ItemTouchListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).id == 0) {
            ITEM_TYPE_LOADER
        } else {
            ITEM_TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_NORMAL) {
            val mUserBinding: ItemUserBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.list_item_user, parent, false)
            UserViewHolder(mUserBinding)
        } else {
            val mLoadingBinding: ItemLoadingBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.list_item_loader, parent, false)
            LoaderViewHolder(mLoadingBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val clickListener: View.OnClickListener = View.OnClickListener {
            listener.onItemClick(getItem(holder.adapterPosition))
        }

        when (holder.itemViewType) {
            ITEM_TYPE_NORMAL -> {
                holder as UserViewHolder
                holder.setUser(getItem(position))
                holder.setClickListener(clickListener)
            }
            ITEM_TYPE_LOADER -> {
                // Do Nothing
            }
        }
    }

    private fun getLoaderItem(): User {
        return User(
            id = 0,
            name = Name(),
            picture = Picture(),
            location = Location(),
            email = "",
            dob = Dob(),
            cell = "",
            gender = ""
        )
    }

    fun addLoader() {
        if (!isLoading()) {
            val newList = ArrayList(currentList)
            newList.add(getLoaderItem())
            submitList(newList)
        }
    }

    fun removeLoader() {
        if (isLoading()) {
            if (currentList.isNotEmpty()) {
                val newList = ArrayList(currentList)
                newList.remove(getLoaderItem())
                submitList(newList)
            }
        }
    }

    fun isLoading(): Boolean {
        return currentList.isEmpty() || currentList.last().id == 0
    }

    interface ItemTouchListener {
        fun onItemClick(user: User)
    }
}