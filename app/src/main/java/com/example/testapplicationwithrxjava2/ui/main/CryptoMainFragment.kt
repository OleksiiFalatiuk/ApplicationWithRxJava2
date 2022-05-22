package com.example.testapplicationwithrxjava2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplicationwithrxjava2.R
import com.example.testapplicationwithrxjava2.databinding.FragmentCryptoMainBinding
import com.example.testapplicationwithrxjava2.models.CryptoMain
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoMainFragment : Fragment() {

    private var _binding: FragmentCryptoMainBinding? = null
    private val mBinding get() = _binding!!

    private val viewRecipeModel by viewModel<CryptoMainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryptoMainBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.recyclerCrypto.apply {
            this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

            this.adapter = CryptoMainAdapter()

            this.adapter = adapter
        }
    }


}