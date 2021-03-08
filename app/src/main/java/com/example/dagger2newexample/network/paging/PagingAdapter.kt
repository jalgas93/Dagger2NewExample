package com.example.dagger2newexample.network.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dagger2newexample.databinding.ItemMainFragmentBinding
import com.example.dagger2newexample.network.RetrofitModel

class PagingAdapter :
    PagingDataAdapter<RetrofitModel, PagingAdapter.PagingViewHolder>(REPO_COMPARATOR) {

    inner class PagingViewHolder(var binding: ItemMainFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var title = binding.tvTitle
        var rating = binding.tvRating

        fun bind(retrofitModel: RetrofitModel) {
            title.text = retrofitModel.title
            rating.text = retrofitModel.rating.toString()

            Glide.with(itemView).load(retrofitModel.featuredImage).into(binding.ivItemMain)

        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RetrofitModel>() {
            override fun areItemsTheSame(oldItem: RetrofitModel, newItem: RetrofitModel): Boolean =
                oldItem.pk == newItem.pk

            override fun areContentsTheSame(
                oldItem: RetrofitModel,
                newItem: RetrofitModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        var repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        var view =
            ItemMainFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingViewHolder(view)
    }
}