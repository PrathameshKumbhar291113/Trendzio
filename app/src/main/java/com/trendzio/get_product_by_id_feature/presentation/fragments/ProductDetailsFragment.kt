package com.trendzio.get_product_by_id_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.trendzio.databinding.FragmentProductDetailsBinding
import com.trendzio.get_product_by_id_feature.presentation.adapter.ProductImageAdapter
import com.trendzio.get_product_by_id_feature.presentation.viewmodel.ProductDetailViewModel
import com.trendzio.get_products_list_feature.presentation.fragments.ProductListFragment.Companion.PRODUCT_ID
import com.trendzio.network.modles.GetProductByIdResponse
import com.trendzio.utils.NetworkResult

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val productDetailViewModel: ProductDetailViewModel by activityViewModels()
    private lateinit var productImageAdapter : ProductImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.progressBar.isVisible = true
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        getProductIdFromArgument()
    }

    private fun getProductIdFromArgument() {
        arguments?.getInt(PRODUCT_ID)?.let {
            productDetailViewModel.getProductIdFromProductListFragment(it)
        }
    }

    private fun setUpObservers() {
        productDetailViewModel.productDetail.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.scrollView.isVisible = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.isVisible = false
                    binding.scrollView.isVisible = true

                    it.data?.body()?.let { data ->
                        setupUi(data)
                    }

                }

                is NetworkResult.Error -> {
                    binding.progressBar.isVisible = false
                    binding.scrollView.isVisible = true
                    Toast.makeText(requireContext(), it.data?.errorBody().toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupUi(data: GetProductByIdResponse) {
        with(binding) {
            thumbnailImage.load(data.thumbnail)
            productName.text = data.title
            productDescription.text = data.description
            productPrice.text = "₹${data.price}/-"
            productDiscountPercentage.text = "(${data.discountPercentage}%)"
            ratingValue.text = data.rating.toString()
            productBrandValue.text = data.brand
            productCategoryValue.text = data.category

            setupProductImagesRecyclerView(data.images)
        }
    }

    private fun setupProductImagesRecyclerView(images: List<String?>?) {
        images?.let {imagesList ->
            productImageAdapter = ProductImageAdapter(imagesList.filterNotNull())
            binding.productImagesRecyclerView.adapter = productImageAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        productDetailViewModel.getProductDetailById()
    }
}