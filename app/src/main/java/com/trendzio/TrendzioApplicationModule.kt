package com.trendzio

import com.trendzio.network.ApiCommunicator
import com.trendzio.utils.RestConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrendzioApplicationModule {
    @Provides
    @Singleton
    fun provideApiCommunicator(): ApiCommunicator {
        return Retrofit.Builder()
            .baseUrl(RestConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCommunicator::class.java)
    }
}