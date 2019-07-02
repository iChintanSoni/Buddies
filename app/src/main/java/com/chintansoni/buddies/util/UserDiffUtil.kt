package com.chintansoni.buddies.util

import androidx.recyclerview.widget.DiffUtil
import com.chintansoni.buddies.model.local.entity.User

class UserDiffUtil :
    DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
