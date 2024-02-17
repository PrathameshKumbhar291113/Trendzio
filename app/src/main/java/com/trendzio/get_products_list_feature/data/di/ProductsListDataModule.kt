package com.trendzio.get_products_list_feature.data.di

import com.trendzio.get_products_list_feature.data.repository.ProductsListRepositoryImpl
import com.trendzio.get_products_list_feature.domain.repository.ProductsListRepository
import com.trendzio.network.ApiCommunicator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsListDataModule {
    @Provides
    @Singleton
    fun providesProductsListRepository(apiCommunicator: ApiCommunicator): ProductsListRepository{
        return ProductsListRepositoryImpl(apiCommunicator)
    }
}