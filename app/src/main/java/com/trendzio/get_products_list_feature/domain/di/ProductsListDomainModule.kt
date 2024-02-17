package com.trendzio.get_products_list_feature.domain.di

import com.trendzio.get_products_list_feature.domain.repository.ProductsListRepository
import com.trendzio.get_products_list_feature.domain.use_case.ProductsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsListDomainModule {
    @Provides
    @Singleton
    fun providesProductsListUseCase(
        productsListRepository: ProductsListRepository
    ): ProductsListUseCase{
        return ProductsListUseCase(productsListRepository)
    }
}