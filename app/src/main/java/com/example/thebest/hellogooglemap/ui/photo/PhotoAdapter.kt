package com.example.thebest.hellogooglemap.ui.photo

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.thebest.hellogooglemap.R
import com.example.thebest.hellogooglemap.databinding.ItemPhotoBinding
import com.example.thebest.hellogooglemap.databinding.ItemPostBinding
import com.example.thebest.hellogooglemap.model.Photo
import com.example.thebest.hellogooglemap.model.Post

/**
 * Adapter for the list of the posts
 * @property context Context in which the application is running
 */
class PhotoAdapter(private val context: Context) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemPhotoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_photo, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder?.bind(photos[position])
        holder?.bindImage(context , photos[position])
    }

    /**
     * The list of posts of the adapter
     */
    private var photos: List<Photo> = listOf()

    override fun getItemCount(): Int {
        return photos.size
    }

    /**
     * Updates the list of posts of the adapter
     * @param posts the new list of posts of the adapter
     */
    fun updatePhotos(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(photo: Photo) {
            binding.photo = photo
            binding.executePendingBindings()
        }

        fun bindImage(context: Context ,photo: Photo) {
            Glide.with(context)
                    .load(photo.url)
                    .into(binding.imagePhoto)
        }
    }
}