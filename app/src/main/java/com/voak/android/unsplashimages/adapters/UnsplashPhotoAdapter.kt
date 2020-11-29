package com.voak.android.unsplashimages.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.paging.rxjava2.RxPagingSource
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.voak.android.unsplashimages.R
import com.voak.android.unsplashimages.databinding.ItemUnsplashPhotoBinding
import com.voak.android.unsplashimages.models.UnsplashPhoto

class UnsplashPhotoAdapter : PagingDataAdapter<UnsplashPhoto, UnsplashPhotoAdapter.UnsplashPhotoViewHolder>(PhotosComparator) {

    override fun onBindViewHolder(holder: UnsplashPhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_unsplash_photo, parent, false)
        val binding = ItemUnsplashPhotoBinding.bind(view)

        return UnsplashPhotoViewHolder(binding)
    }

    inner class UnsplashPhotoViewHolder(private val binding: ItemUnsplashPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnsplashPhoto) {
            Picasso.get()
                .load(item.urls.regular)
                .error(R.drawable.ic_error)
                .centerCrop()
                .fit()
                .into(binding.imageView)

            binding.userName.text = item.user.userName
        }
    }

    object PhotosComparator: DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

    }
}