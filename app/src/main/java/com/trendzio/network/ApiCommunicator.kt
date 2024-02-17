package com.trendzio.network

import com.trendzio.network.modles.GetProductByIdResponse
import com.trendzio.network.modles.GetProductsListResponse
import com.trendzio.utils.RestConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCommunicator {
    @GET(RestConstants.GET_PRODUCTS_LIST)
    suspend fun getProductsList() : Response<GetProductsListResponse>

    @GET(RestConstants.GET_PRODUCT_BY_ID)
    suspend fun getProductById(@Path ("id") id: Int): Response<GetProductByIdResponse>

}