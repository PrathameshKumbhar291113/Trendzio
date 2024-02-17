package com.trendzio.get_product_by_id_feature.data.di

import com.trendzio.get_product_by_id_feature.data.repository.ProductDetailRepositoryImpl
import com.trendzio.get_product_by_id_feature.domain.repository.ProductDetailRepository
import com.trendzio.network.ApiCommunicator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDetailDataModule {

    @Provides
    @Singleton
    fun providesProductDetailRepository(apiCommunicator: ApiCommunicator): ProductDetailRepository {
        return ProductDetailRepositoryImpl(apiCommunicator)
    }
}