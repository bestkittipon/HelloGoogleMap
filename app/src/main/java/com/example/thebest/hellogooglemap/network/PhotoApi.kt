package com.example.thebest.hellogooglemap.network

import io.reactivex.Observable
import com.example.thebest.hellogooglemap.model.Photo
import retrofit2.http.GET

interface PhotoApi {
    @GET("/photos")
    fun getPhotos(): Observable<List<Photo>>
}