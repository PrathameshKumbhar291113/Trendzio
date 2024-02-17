package com.trendzio.get_product_by_id_feature.data.repository

import com.trendzio.get_product_by_id_feature.domain.repository.ProductDetailRepository
import com.trendzio.network.ApiCommunicator
import com.trendzio.network.modles.GetProductByIdResponse
import retrofit2.Response
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val apiCommunicator: ApiCommunicator
) : ProductDetailRepository {
    override suspend fun getProductById(id: Int): Response<GetProductByIdResponse> {
        return apiCommunicator.getProductById(id)
    }
}