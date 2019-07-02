package com.chintansoni.buddies.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chintansoni.buddies.databinding.ItemUserBinding
import com.chintansoni.buddies.model.local.entity.User

class UserViewHolder(private var mUserBinding: ItemUserBinding) :
    RecyclerView.ViewHolder(mUserBinding.root) {

    fun setUser(user: User) {
        mUserBinding.user = user
    }

    fun setClickListener(clickListener: View.OnClickListener) {
        mUserBinding.root.setOnClickListener(clickListener)
    }
}