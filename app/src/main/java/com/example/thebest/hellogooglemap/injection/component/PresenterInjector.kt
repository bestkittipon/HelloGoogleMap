package com.example.thebest.hellogooglemap.injection.component

import dagger.BindsInstance
import dagger.Component
import com.example.thebest.hellogooglemap.base.BaseView
import com.example.thebest.hellogooglemap.injection.module.ContextModule
import com.example.thebest.hellogooglemap.injection.module.NetworkModule
import com.example.thebest.hellogooglemap.ui.photo.PhotoPresenter
import com.example.thebest.hellogooglemap.ui.post.PostPresenter
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(postPresenter: PostPresenter)

    fun inject(photoPresenter: PhotoPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}