package com.trendzio.get_product_by_id_feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trendzio.get_product_by_id_feature.domain.use_case.ProductDetailUseCase
import com.trendzio.network.modles.GetProductByIdResponse
import com.trendzio.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailUseCase: ProductDetailUseCase
): ViewModel(){

    private val _productDetail = MutableLiveData<NetworkResult<Response<GetProductByIdResponse>>>()
    val productDetail : LiveData<NetworkResult<Response<GetProductByIdResponse>>> = _productDetail

    private val _getProductId = MutableLiveData<Int>()
    private val getProductId : LiveData<Int> = _getProductId

    init {
        getProductDetailById()
    }

    fun getProductDetailById(){
        getProductId.value?.let {
            productDetailUseCase(it).onEach {
                when(it){
                    is NetworkResult.Loading ->{}
                    is NetworkResult.Success ->{
                        _productDetail.postValue(it)
                    }

                    is NetworkResult.Error ->{}
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getProductIdFromProductListFragment(id: Int){
        _getProductId.value = id
    }

}