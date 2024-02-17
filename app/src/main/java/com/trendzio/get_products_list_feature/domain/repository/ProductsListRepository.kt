package com.trendzio.get_products_list_feature.domain.repository

import com.trendzio.network.modles.GetProductsListResponse
import retrofit2.Response

interface ProductsListRepository {

    suspend fun getProductsList(): Response<GetProductsListResponse>
}