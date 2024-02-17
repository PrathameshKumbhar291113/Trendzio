package com.trendzio.get_products_list_feature.domain.use_case

import com.trendzio.get_products_list_feature.domain.repository.ProductsListRepository
import com.trendzio.network.modles.GetProductsListResponse
import com.trendzio.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject


class ProductsListUseCase  @Inject constructor(private val productsListRepository: ProductsListRepository){
    operator fun invoke() = flow<NetworkResult<Response<GetProductsListResponse>>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = productsListRepository.getProductsList()))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}