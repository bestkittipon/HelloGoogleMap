package com.example.thebest.hellogooglemap.ui.post

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.thebest.hellogooglemap.R
import com.example.thebest.hellogooglemap.base.BaseFragment
import com.example.thebest.hellogooglemap.databinding.FragmentPostBinding
import com.example.thebest.hellogooglemap.model.Post

class PostFragment : BaseFragment<PostPresenter>(), PostView  {

    companion object {
        fun newInstance() : PostFragment {
            val args = Bundle()
            val fragment = PostFragment()
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * The adapter for the list of posts
     */
    private lateinit var postsAdapter: PostAdapter

    /**
     * DataBinding instance
     */
    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_post , container , false)
        postsAdapter = PostAdapter(context)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(context)
        binding.dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updatePosts(posts: List<Post>) {
        postsAdapter.updatePosts(posts)
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this)
    }
}