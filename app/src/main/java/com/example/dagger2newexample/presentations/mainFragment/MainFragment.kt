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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger2newexample.App
import com.example.dagger2newexample.R
import com.example.dagger2newexample.databinding.FragmentMainBinding
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
            mainViewModel.getRecipe(9)
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = MainAdapter()
        mBinding.rvMainFragment.apply {
            this.adapter = adapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {


        })
    }

}