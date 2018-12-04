package com.example.thebest.hellogooglemap.ui.post

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.thebest.hellogooglemap.R
import com.example.thebest.hellogooglemap.base.BaseActivity
import com.example.thebest.hellogooglemap.base.BasePresenter
import com.example.thebest.hellogooglemap.base.BaseView
import com.example.thebest.hellogooglemap.databinding.ActivityPostBinding
import com.example.thebest.hellogooglemap.ui.photo.PhotoFragment

/**
 * Activity displaying the list of posts
 */
class PostActivity : BaseActivity<BasePresenter<BaseView>>() {
    override var presenter: BasePresenter<BaseView>?
        get() = null
        set(value) {}

    override fun instantiatePresenter(): BasePresenter<BaseView>? {
        return presenter
    }

    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content_container_post, PostFragment.newInstance())
                addToBackStack(null)
            }.commit()

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content_container_photo, PhotoFragment.newInstance())
                addToBackStack(null)
            }.commit()
        }
    }
}