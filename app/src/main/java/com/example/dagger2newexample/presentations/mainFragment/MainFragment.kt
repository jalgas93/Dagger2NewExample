package com.example.dagger2newexample.presentations.mainFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger2newexample.App
import com.example.dagger2newexample.R
import com.example.dagger2newexample.databinding.FragmentMainBinding
import com.example.dagger2newexample.utils.DataState
import com.example.dagger2newexample.utils.hide
import com.example.dagger2newexample.utils.show
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject




class MainFragment : Fragment() {

//    Важно - лучшие практики
//
//    Activity вставляет Dagger в onCreateметод перед вызовом super.
//
//    Фрагмент вводит Dagger в onAttachметод после вызова super.


    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
           // mainViewModel.getRecipe(8)
        }
        setupRecyclerView()
        itemClick()
    }

    private fun itemClick() {

    }

    private fun setupRecyclerView() {
        val adapter = MainAdapter()
        val searchAdapter  = SearchAdapter()
        mBinding.rvMainFragment.apply {
            this.adapter = adapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }



        adapter.setItemClick {
            var title = it.title
            var description = it.description
            var dataUpdate = it.dateUpdated
            var dataAdded = it.dateAdded
            var rating = it.rating
            var cookingInstruc = it.cookingInstructions
            var featureImage = it.featuredImage
            var pk = it.pk
            var sourceUrl = it.sourceUrl
            var ingredients = it.ingredients

            var action =MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        }


        mainViewModel.search("beef carrot potato onion",5).observe(viewLifecycleOwner, Observer {

            when(it.status){

                DataState.Status.LOADING->{
                 mBinding.progressBar.show()
                }
                DataState.Status.SUCCESS->{
                    mBinding.progressBar.hide()
                    it.data?.let {
                        adapter.submitList(it.results)
                    }


                }
                DataState.Status.ERROR->{
                    mBinding.progressBar.hide()
                }

            }

        })

//
//            mainViewModel.getRecipeRepos(5).observe(viewLifecycleOwner, Observer {
//
//
//                when (it.status) {
//
//                    DataState.Status.LOADING -> {
//                        mBinding.progressBar.show()
//
//                    }
//                    DataState.Status.SUCCESS -> {
//                        mBinding.progressBar.hide()
//                        it.data?.let {
//                            adapter.submitList(listOf(it))
//                        }
//
//                    }
//                    DataState.Status.ERROR -> {
//                        mBinding.progressBar.hide()
//                        //Snackbar.make(_binding?.coordinatorLayout,it.message!!,Snackbar.LENGTH_SHORT).show()
//
//                    }
//
//
//                }
//
//
//            })


    }

}