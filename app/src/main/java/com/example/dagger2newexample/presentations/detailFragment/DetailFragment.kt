package com.example.dagger2newexample.presentations.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dagger2newexample.R
import com.example.dagger2newexample.databinding.FragmentDetailBinding
import javax.inject.Inject


class DetailFragment : Fragment() {

    @Inject
    lateinit var detailViewModel: DetailViewModel

    private var _binding:FragmentDetailBinding? = null
    private val mBinding get() = _binding!!
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
    }

}