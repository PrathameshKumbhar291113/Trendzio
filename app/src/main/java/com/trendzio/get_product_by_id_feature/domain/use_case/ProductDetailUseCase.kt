package com.trendzio.get_product_by_id_feature.domain.use_case

import com.trendzio.get_product_by_id_feature.domain.repository.ProductDetailRepository
import com.trendzio.network.modles.GetProductByIdResponse
import com.trendzio.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(private val productDetailRepository: ProductDetailRepository){
    operator fun invoke(id: Int) = flow<NetworkResult<Response<GetProductByIdResponse>>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = productDetailRepository.getProductById(id)))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}