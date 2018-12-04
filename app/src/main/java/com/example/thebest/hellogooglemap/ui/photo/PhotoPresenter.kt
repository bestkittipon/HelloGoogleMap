package com.example.thebest.hellogooglemap.ui.photo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.thebest.hellogooglemap.R
import com.example.thebest.hellogooglemap.base.BasePresenter
import com.example.thebest.hellogooglemap.network.PhotoApi
import javax.inject.Inject

class PhotoPresenter(photoView: PhotoView) : BasePresenter<PhotoView>(photoView) {
    @Inject
    lateinit var photoApi: PhotoApi

    override fun onViewCreated() {
        loadPhotos()
    }

    fun loadPhotos() {
        view.showLoading()
        subscription = photoApi
                .getPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { photoList -> view.updatePhotos(photoList) },
                        { error -> view.showError(error.message!!) }
                )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}