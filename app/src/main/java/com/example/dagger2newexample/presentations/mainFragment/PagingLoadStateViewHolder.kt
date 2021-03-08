package com.example.dagger2newexample.presentations.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger2newexample.R
import com.example.dagger2newexample.databinding.LoadingErrorStatePagingBinding

class PagingLoadStateViewHolder(
    private val binding: LoadingErrorStatePagingBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.also {
            it.setOnClickListener { retry.invoke() }
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }

        binding.retryButton.isVisible = loadState is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
        binding.progressBar.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PagingLoadStateViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.loading_error_state_paging, parent, false)
            val view = LoadingErrorStatePagingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            //val binding = LoadingErrorStatePagingBinding.bind(view)
            return PagingLoadStateViewHolder(view, retry)
        }
    }
}