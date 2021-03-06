package com.example.dagger2newexample.presentations.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger2newexample.databinding.ItemMainFragmentBinding
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.paging.PagingModel

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


    private val diffUtilItemCallback = object : DiffUtil.ItemCallback<PagingModel>() {
        override fun areItemsTheSame(oldItem: PagingModel, newItem: PagingModel): Boolean {
            return oldItem.count == newItem.count
        }

        override fun areContentsTheSame(oldItem: PagingModel, newItem: PagingModel): Boolean {
            return oldItem.count == newItem.count
        }

    }
    private val listDiffer = AsyncListDiffer(this, diffUtilItemCallback)
    //private lateinit var binding: ItemMainFragmentBinding


    inner class SearchViewHolder(var binding:ItemMainFragmentBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(pagingModel: PagingModel){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
       var view = ItemMainFragmentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
     holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    fun submitList(list: List<PagingModel>?) {
        listDiffer.submitList(list)
    }


}