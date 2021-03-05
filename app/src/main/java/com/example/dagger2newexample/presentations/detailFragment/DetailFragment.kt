package com.example.dagger2newexample.presentations.detailFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger2newexample.App
import com.example.dagger2newexample.databinding.FragmentDetailBinding
import com.example.dagger2newexample.network.RetrofitModel
import javax.inject.Inject


class DetailFragment : Fragment() {


//    Важно - лучшие практики
//
//    Activity вставляет Dagger в onCreateметод перед вызовом super.
//
//    Фрагмент вводит Dagger в onAttachметод после вызова super.


    private var _binding: FragmentDetailBinding? = null
    private val mBinding get() = _binding!!
    private val args:DetailFragmentArgs by navArgs()



    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    @Inject
    lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var a = args.data
        toolbar()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        var adapter = DetailAdapter()
        mBinding.rvDetailFragment.apply {
            this.adapter = adapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        adapter.setItemClick {
            var action = DetailFragmentDirections.actionDetailFragmentToWebFragment(it)
            view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        }
           var a = listOf(args.data)
          adapter.submitList(a as List<RetrofitModel>)
    }


    private fun toolbar() {
        mBinding.tvToolbar.text = args.data?.title
        mBinding.ivToolbar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


}