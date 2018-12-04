package com.example.thebest.hellogooglemap.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment<P : BasePresenter<BaseView>> : BaseView , Fragment() {
    protected lateinit var presenter: P

    protected abstract fun instantiatePresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    override fun getContext(): Context {
        return this.activity as Context
    }
}