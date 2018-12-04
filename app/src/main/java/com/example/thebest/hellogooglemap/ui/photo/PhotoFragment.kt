package com.example.thebest.hellogooglemap.ui.photo

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
import com.example.thebest.hellogooglemap.databinding.FragmentPhotoBinding
import com.example.thebest.hellogooglemap.model.Photo

class PhotoFragment : BaseFragment<PhotoPresenter>(), PhotoView  {

    companion object {
        fun newInstance() : PhotoFragment {
            val args = Bundle()
            val fragment = PhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * The adapter for the list of posts
     */
    private lateinit var photoAdapter: PhotoAdapter

    /**
     * DataBinding instance
     */
    private lateinit var binding: FragmentPhotoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_photo , container , false)
        photoAdapter = PhotoAdapter(context)
        binding.adapter = photoAdapter
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

    override fun updatePhotos(photos: List<Photo>) {
        photoAdapter.updatePhotos(photos)
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

    override fun instantiatePresenter(): PhotoPresenter {
        return PhotoPresenter(this)
    }
}