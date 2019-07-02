package com.chintansoni.buddies.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chintansoni.buddies.R
import com.chintansoni.buddies.databinding.DetailFragmentBinding
import com.chintansoni.buddies.model.local.entity.User
import kotlinx.android.synthetic.main.frag_details.*

class DetailFragment : Fragment() {

    private lateinit var mDetailFragmentBinding: DetailFragmentBinding
    private var user: User? = null

    companion object {
        const val detailFragmentTag = "details_fragment"
        const val ARG_USER: String = "User"
        fun newInstance(user: User): DetailFragment {
            val detailsFragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            detailsFragment.arguments = bundle
            return detailsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(ARG_USER) as? User
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.frag_details, container, false)
        return mDetailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        user?.let {
            mDetailFragmentBinding.user = it
        }
        ivClose.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}