package com.chintansoni.buddies.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chintansoni.buddies.R
import com.chintansoni.buddies.model.local.entity.User
import com.chintansoni.buddies.view.adapter.UserRecyclerAdapter
import com.chintansoni.buddies.view.fragment.DetailFragment
import com.chintansoni.buddies.view.fragment.ListFragment

class UsersActivity : AppCompatActivity(), UserRecyclerAdapter.ItemTouchListener {
    override fun onItemClick(user: User) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_up,
                R.anim.slide_down,
                R.anim.slide_up,
                R.anim.slide_down
            )
            .add(R.id.container, DetailFragment.newInstance(user))
            .addToBackStack(DetailFragment.detailFragmentTag)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commit()
        }
    }
}
