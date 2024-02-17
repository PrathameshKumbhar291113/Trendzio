package com.trendzio.get_products_list_feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trendzio.get_products_list_feature.domain.use_case.ProductsListUseCase
import com.trendzio.network.modles.GetProductsListResponse
import com.trendzio.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productsListUseCase: ProductsListUseCase
): ViewModel(){

    private val _productsList = MutableLiveData<NetworkResult<Response<GetProductsListResponse>>>()
    val productsList : LiveData<NetworkResult<Response<GetProductsListResponse>>> = _productsList

    init {
        getProductsList()
    }

    fun getProductsList(){
        productsListUseCase().onEach {
            when (it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    _productsList.postValue(it)
                }
                is NetworkResult.Error -> {}
            }
        }.launchIn(viewModelScope)
    }
}