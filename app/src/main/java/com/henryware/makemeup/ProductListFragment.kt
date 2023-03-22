package com.henryware.makemeup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.henryware.makemeup.databinding.FragmentProductListBinding
import com.henryware.makemeup.utils.Resource
import com.henryware.makemeup.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_product_list, container, false)
        return binding.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)
        setUpView()
        initObservers()
        productsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("item", it)
            }
            findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment, bundle)
        }
    }

    private fun initObservers() {
        viewModel.products.observe(viewLifecycleOwner) { result ->
            productsAdapter.differ.submitList(result.data?.toMutableList())
            binding.progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
        }
    }

    private fun setUpView() {
        productsAdapter = ProductsAdapter()
        binding.productsRecyclerview.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}
