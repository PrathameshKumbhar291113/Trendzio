package com.trendzio.get_products_list_feature.data.repository

import com.trendzio.get_products_list_feature.domain.repository.ProductsListRepository
import com.trendzio.network.ApiCommunicator
import com.trendzio.network.modles.GetProductsListResponse
import retrofit2.Response
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val apiCommunicator: ApiCommunicator) : ProductsListRepository{
    override suspend fun getProductsList(): Response<GetProductsListResponse> {
        return apiCommunicator.getProductsList()
    }
}