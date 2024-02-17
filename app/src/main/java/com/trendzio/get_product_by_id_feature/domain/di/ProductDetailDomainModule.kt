package com.trendzio.get_product_by_id_feature.domain.di

import com.trendzio.get_product_by_id_feature.domain.repository.ProductDetailRepository
import com.trendzio.get_product_by_id_feature.domain.use_case.ProductDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDetailDomainModule {
    @Provides
    @Singleton
    fun providesProductDetailUseCase(
        productDetailRepository: ProductDetailRepository
    ): ProductDetailUseCase{
        return ProductDetailUseCase(productDetailRepository)
    }
}