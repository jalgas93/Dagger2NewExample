package com.example.dagger2newexample.presentations.mainFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dagger2newexample.App
import com.example.dagger2newexample.R
import com.example.dagger2newexample.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment() {

//    Важно - лучшие практики
//
//    Activity вставляет Dagger в onCreateметод перед вызовом super.
//
//    Фрагмент вводит Dagger в onAttachметод после вызова super.

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    @Inject
    lateinit var mainViewModel: MainViewModel


    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

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


    }

}