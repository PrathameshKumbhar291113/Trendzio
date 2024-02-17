package com.trendzio.get_products_list_feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.trendzio.databinding.ItemProductCardBinding
import com.trendzio.get_products_list_feature.presentation.manager.calculateOriginalPrice
import com.trendzio.network.modles.GetProductsListResponse

class ProductAdapter (
    private val productList: List<GetProductsListResponse.Product>
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        return ProductViewHolder(
            ItemProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int)  = holder.bind(productList[position])

    override fun getItemCount(): Int = productList.size

    inner class ProductViewHolder(
        private val binding: ItemProductCardBinding
    ): ViewHolder(binding.root){
        fun bind(product : GetProductsListResponse.Product){
            with(product) {
                binding.productName.text = title.toString()
                binding.ratingValue.text = rating.toString()
                binding.pricingValue.text = "₹${price.toString()}"
                binding.discountedValue.text = "(${discountPercentage.toString()}% off)"
                binding.mrpValue.text =
                    "₹${calculateOriginalPrice(price?.toDouble(), discountPercentage)}"
                binding.productIcon.load(images?.get(0))
            }
        }
    }
}