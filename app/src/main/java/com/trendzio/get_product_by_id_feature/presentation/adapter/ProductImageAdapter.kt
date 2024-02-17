package com.trendzio.get_product_by_id_feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trendzio.databinding.ItemProductImagesBinding

class ProductImageAdapter(
    private val productImagesList : List<String>
) : RecyclerView.Adapter<ProductImageAdapter.ProductImagesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesViewHolder {
        return ProductImagesViewHolder(
            ItemProductImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductImagesViewHolder, position: Int)  = holder.bind(productImagesList[position])

    override fun getItemCount(): Int = productImagesList.size

    inner class ProductImagesViewHolder(
        private val binding: ItemProductImagesBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){
            binding.productImage.load(data)
        }
    }

}