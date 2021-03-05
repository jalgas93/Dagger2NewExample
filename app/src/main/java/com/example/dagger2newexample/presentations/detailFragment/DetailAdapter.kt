package com.example.dagger2newexample.presentations.detailFragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dagger2newexample.databinding.FragmentDetailBinding
import com.example.dagger2newexample.databinding.ItemDetailFragmentBinding
import com.example.dagger2newexample.databinding.ItemMainFragmentBinding
import com.example.dagger2newexample.network.RetrofitModel

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {


          private lateinit var itemClick:(url:String?)->Unit
    fun setItemClick(itemClick:(url:String?)->Unit){
        this.itemClick = itemClick
    }

    private val diffUtilItemCallback = object : DiffUtil.ItemCallback<RetrofitModel>() {
        override fun areItemsTheSame(oldItem: RetrofitModel, newItem: RetrofitModel): Boolean {
            return oldItem.pk == newItem.pk
        }

        override fun areContentsTheSame(oldItem: RetrofitModel, newItem: RetrofitModel): Boolean {
            return oldItem.pk == newItem.pk
        }
    }
    private val listDiffer = AsyncListDiffer(this, diffUtilItemCallback)

    //private lateinit var binding: ItemMainFragmentBinding
    inner class DetailViewHolder(var binding: ItemDetailFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var rating = binding.tvRating
        var description = binding.tvDetailDescription
        var data = binding.tvData
        var sampleText = binding.tvText
        fun bind(retrofitModel: RetrofitModel) {
            rating.text = retrofitModel.rating.toString()

            data.text = retrofitModel.dateUpdated
            sampleText.append(retrofitModel.publisher)

            retrofitModel.ingredients?.forEachIndexed { index, value ->
                Log.i("jalgas2", "${index}" + "${value}")
                    description.append( "${value}" + "\n")
            }

            itemView.setOnClickListener {
                itemClick.invoke(retrofitModel.sourceUrl)
            }

            Glide.with(itemView).load(retrofitModel.featuredImage).into(binding.ivItemMain)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view =
            ItemDetailFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    fun submitList(list: List<RetrofitModel>) {
        listDiffer.submitList(list)
    }

}