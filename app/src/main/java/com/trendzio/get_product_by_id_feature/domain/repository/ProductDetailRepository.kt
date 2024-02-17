package com.trendzio.get_product_by_id_feature.domain.repository

import com.trendzio.network.modles.GetProductByIdResponse
import retrofit2.Response

interface ProductDetailRepository {
    suspend fun getProductById(id: Int) : Response<GetProductByIdResponse>
}