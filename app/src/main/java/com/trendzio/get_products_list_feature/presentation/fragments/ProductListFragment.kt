package com.trendzio.get_products_list_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.trendzio.R
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

    companion object {
        const val PRODUCT_ID = "product_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        binding.progressBar.isVisible = true
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
                    binding.productsListRecyclerView.isVisible = false
                }

                is NetworkResult.Success -> {
                    binding.progressBar.isVisible = false
                    binding.productsListRecyclerView.isVisible = true
                    it.data?.body()?.products?.let { data ->
                        setupRecyclerView(data)
                    }
                }

                is NetworkResult.Error -> {
                    binding.progressBar.isVisible = false
                    binding.productsListRecyclerView.isVisible = true
                    Toast.makeText(requireContext(), it.data?.errorBody().toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView(data: List<GetProductsListResponse.Product?>) {

        productAdapter = ProductAdapter(data.filterNotNull(), this::onClick)
        binding.productsListRecyclerView.adapter = productAdapter

    }

    private fun onClick(data: GetProductsListResponse.Product) {
        val productIdBundle: Bundle = Bundle().apply {
            data.id?.let {productId ->
                putInt(PRODUCT_ID, productId)
            }
        }
        findNavController().navigate(R.id.productDetailsFragment, productIdBundle)
    }

    override fun onResume() {
        super.onResume()

        productListViewModel.getProductsList()

    }

}