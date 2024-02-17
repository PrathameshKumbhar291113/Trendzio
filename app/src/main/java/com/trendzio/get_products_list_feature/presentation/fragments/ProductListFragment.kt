package com.trendzio.get_products_list_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.trendzio.databinding.FragmentProductListBinding
import com.trendzio.get_products_list_feature.presentation.adapter.ProductAdapter
import com.trendzio.get_products_list_feature.presentation.viewmodel.ProductListViewModel
import com.trendzio.network.modles.GetProductsListResponse
import com.trendzio.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private val productListViewModel: ProductListViewModel by activityViewModels()
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObservers()
    }

    private fun setupUi() {

    }

    private fun setupObservers() {
        productListViewModel.productsList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    it.data?.body()?.products?.let { data ->
                        setupRecyclerView(data)
                    }
                }

                is NetworkResult.Error -> {

                }
            }
        }
    }

    private fun setupRecyclerView(data: List<GetProductsListResponse.Product?>) {

        productAdapter = ProductAdapter(data.filterNotNull())
        binding.productsListRecyclerView.adapter = productAdapter

    }

}