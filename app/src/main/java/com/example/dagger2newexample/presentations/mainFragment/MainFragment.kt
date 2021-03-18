package com.example.dagger2newexample.presentations.mainFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger2newexample.App
import com.example.dagger2newexample.R
import com.example.dagger2newexample.databinding.FragmentMainBinding
import com.example.dagger2newexample.network.paging.PagingAdapter
import com.example.dagger2newexample.utils.DataState
import com.example.dagger2newexample.utils.hide
import com.example.dagger2newexample.utils.show
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
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
    var pagingAdapter = PagingAdapter()
    private var searchJob: Job? = null

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
        //  setupRecyclerView()

        innerAdapter()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        search(query)
        initSearch(query)
        // showEmptyList(show = true)
        mBinding.retryButton.setOnClickListener { pagingAdapter.retry() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, mBinding.etSearch.text?.trim().toString())
    }

    private fun initSearch(query: String) {
        mBinding.etSearch.setText(query)
        mBinding.etSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
        mBinding.etSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }


        lifecycleScope.launch {
            pagingAdapter.loadStateFlow
                .distinctUntilChangedBy {
                    it.refresh
                }
                .filter {
                    it.refresh is LoadState.NotLoading
                }

                .collect {
                    mBinding.rvMainFragment.scrollToPosition(0)
                }
        }
    }

    private fun updateRepoListFromInput() {
        mBinding.etSearch.text!!.trim().let {
            if (it.isNotEmpty()) {
               // mBinding.rvMainFragment.scrollToPosition(0)
                search(it.toString())
                Log.i("jalgas7",it.toString())


            } else {

            }
        }
    }

    private fun innerAdapter() {
        mBinding.rvMainFragment.adapter = pagingAdapter.withLoadStateFooter(
             footer = PagingLoadStateAdapter{pagingAdapter.retry()}
        )



        pagingAdapter.addLoadStateListener {
            mBinding.rvMainFragment.isVisible = it.source.refresh is LoadState.NotLoading
            mBinding.progressBar.isVisible = it.source.refresh is LoadState.Loading
            mBinding.retryButton.isVisible = it.source.refresh is LoadState.Error


            val errorState = it.source.append as? LoadState.Error
                ?: it.source.prepend as? LoadState.Error
                ?: it.append as? LoadState.Error
                ?: it.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(requireContext(),"LoadState",Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun search(query: String) {
        mBinding.rvMainFragment.apply {
            this.adapter = pagingAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            mainViewModel.searchRepo(query).collectLatest {
                pagingAdapter.submitData(it)
                Log.i("jalgas6",it.toString())
            }
        }
    }


    //
//    private fun setupRecyclerView() {
//        val adapter = MainAdapter()
//        val searchAdapter  = SearchAdapter()
//        mBinding.rvMainFragment.apply {
//            this.adapter = adapter
//            layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        }
//
//
//
//        adapter.setItemClick {
//            var title = it.title
//            var description = it.description
//            var dataUpdate = it.dateUpdated
//            var dataAdded = it.dateAdded
//            var rating = it.rating
//            var cookingInstruc = it.cookingInstructions
//            var featureImage = it.featuredImage
//            var pk = it.pk
//            var sourceUrl = it.sourceUrl
//            var ingredients = it.ingredients
//
//            var action =MainFragmentDirections.actionMainFragmentToDetailFragment(it)
//            view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
//        }
//
//
//        mainViewModel.search("beef carrot potato onion",5).observe(viewLifecycleOwner, Observer {
//
//            when(it.status){
//
//                DataState.Status.LOADING->{
//                 mBinding.progressBar.show()
//                }
//                DataState.Status.SUCCESS->{
//                    mBinding.progressBar.hide()
//                    it.data?.let {
//                        adapter.submitList(it.results)
//                    }
//
//
//                }
//                DataState.Status.ERROR->{
//                    mBinding.progressBar.hide()
//                }
//
//            }
//
//        })
//
////
////            mainViewModel.getRecipeRepos(5).observe(viewLifecycleOwner, Observer {
////
////
////                when (it.status) {
////
////                    DataState.Status.LOADING -> {
////                        mBinding.progressBar.show()
////
////                    }
////                    DataState.Status.SUCCESS -> {
////                        mBinding.progressBar.hide()
////                        it.data?.let {
////                            adapter.submitList(listOf(it))
////                        }
////
////                    }
////                    DataState.Status.ERROR -> {
////                        mBinding.progressBar.hide()
////                        //Snackbar.make(_binding?.coordinatorLayout,it.message!!,Snackbar.LENGTH_SHORT).show()
////
////                    }
////
////
////                }
////
////
////            })
//
//
//    }


    private fun showEmptyList(show: Boolean) {
        if (show) {
            //  mBinding.tvNotFound.visibility = View.VISIBLE
            //  mBinding.rvMainFragment.visibility = View.GONE
        } else {
            //  mBinding.tvNotFound.visibility = View.GONE
            //  mBinding.rvMainFragment.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = " "
    }
}