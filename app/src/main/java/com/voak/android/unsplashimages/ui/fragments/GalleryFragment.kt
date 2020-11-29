package com.voak.android.unsplashimages.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.voak.android.unsplashimages.R
import com.voak.android.unsplashimages.adapters.UnsplashPhotoAdapter
import com.voak.android.unsplashimages.adapters.UnsplashPhotoLoadStateAdapter
import com.voak.android.unsplashimages.databinding.FragmentGalleryBinding
import com.voak.android.unsplashimages.ui.viewModels.GalleryViewModel
import io.reactivex.disposables.Disposable

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel by viewModels<GalleryViewModel>()
    private lateinit var mAdapter: UnsplashPhotoAdapter
    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        binding = FragmentGalleryBinding.bind(view)
        binding.viewModel = viewModel

        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = UnsplashPhotoAdapter()

        binding.recyclerView.apply {
            this.adapter = mAdapter.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter() { mAdapter.retry() },
                footer = UnsplashPhotoLoadStateAdapter() { mAdapter.retry() }
            )
        }

        disposable = binding.viewModel?.photos?.subscribe { data ->
            mAdapter.submitData(viewLifecycleOwner.lifecycle, data)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            @SuppressLint("CheckResult")
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    disposable?.dispose()
                    disposable = binding.viewModel?.getSearchResult(query)?.subscribe { data ->
                        mAdapter.submitData(viewLifecycleOwner.lifecycle, data)
                    }
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

}