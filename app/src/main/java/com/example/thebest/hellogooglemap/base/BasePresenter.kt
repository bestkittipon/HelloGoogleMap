package com.example.thebest.hellogooglemap.base

import io.reactivex.disposables.Disposable
import com.example.thebest.hellogooglemap.injection.component.DaggerPresenterInjector
import com.example.thebest.hellogooglemap.injection.component.PresenterInjector
import com.example.thebest.hellogooglemap.injection.module.ContextModule
import com.example.thebest.hellogooglemap.injection.module.NetworkModule
import com.example.thebest.hellogooglemap.ui.photo.PhotoPresenter
import com.example.thebest.hellogooglemap.ui.post.PostPresenter

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property injector The injector used to inject required dependencies
 * @constructor Injects the required dependencies
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()

    protected var subscription: Disposable? = null

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostPresenter -> injector.inject(this)
            is PhotoPresenter -> injector.inject(this)
        }
    }
}