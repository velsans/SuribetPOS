package com.suribetpos.main.data.api

import com.suribetpos.main.utils.Common
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Velmurugan on 2/15/2021.
 */
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ServiceURL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: TopupApi by lazy {
        retrofit.create(TopupApi::class.java)
    }

    private val card_retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Common.CardValidation_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val card_api: TopupApi by lazy {
        card_retrofit.create(TopupApi::class.java)
    }
}