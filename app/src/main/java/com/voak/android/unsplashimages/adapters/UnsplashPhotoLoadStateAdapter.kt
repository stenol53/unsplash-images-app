package com.voak.android.unsplashimages.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voak.android.unsplashimages.R
import com.voak.android.unsplashimages.databinding.PhotoLoadStateFooterBinding

class UnsplashPhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UnsplashPhotoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_load_state_footer, parent, false)
        val binding = PhotoLoadStateFooterBinding.bind(view)

        return LoadStateViewHolder(binding)
    }


    inner class LoadStateViewHolder(private val binding: PhotoLoadStateFooterBinding ) :
        RecyclerView.ViewHolder(binding.root)
    {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.buttonRetry.isVisible = loadState !is LoadState.Loading
            binding.textViewError.isVisible = loadState !is LoadState.Loading
        }
    }
}