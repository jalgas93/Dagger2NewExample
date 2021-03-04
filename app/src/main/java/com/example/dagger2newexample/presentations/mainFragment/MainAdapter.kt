package com.example.dagger2newexample.presentations.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dagger2newexample.databinding.ItemMainFragmentBinding
import com.example.dagger2newexample.network.RetrofitModel


class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


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


inner class MainViewHolder(val binding: ItemMainFragmentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var image = binding.ivItemMain
    var title = binding.tvTitleMain
    var data = binding.tvData
    var text = binding.tvText

        fun bind(model: RetrofitModel) {
            title.text = model.title
            Glide.with(itemView).load(model.featuredImage).into(binding.ivItemMain)

        }

}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    var view = ItemMainFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MainViewHolder(view)
}

override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       holder.bind(listDiffer.currentList[position])
}

    override fun getItemCount(): Int = listDiffer.currentList.size

    fun submitList(list: List<RetrofitModel>) {
        listDiffer.submitList(list)
    }


}







//
//
//
//var model:List<RetrofitModel> = listOf()
//    set(value) {
//        field = value
//        notifyDataSetChanged()
//    }
//
//inner class MainViewHolder(var binding: ItemMainFragmentBinding):RecyclerView.ViewHolder(binding.root){
//
//    var simpleText = binding.tvText
//    var data = binding.tvData
//    var title = binding.tvTitleMain
//
//    fun bind(retrofitModel: RetrofitModel){
//
//        simpleText.text = retrofitModel.publisher
//        data.text = retrofitModel.dateUpdated
//        title.text = retrofitModel.title
//
//
//        Glide.with(itemView).load(retrofitModel.featuredImage).into(binding.ivItemMain)
//    }
//
//}
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
//    var view = ItemMainFragmentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//    return MainViewHolder(view)
//}
//
//override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//    holder.bind(model[position])
//}
//
//override fun getItemCount(): Int = model.size