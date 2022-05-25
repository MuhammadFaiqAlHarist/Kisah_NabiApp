package com.faiq.kisahnabiapp.data.network

import com.faiq.kisahnabiapp.data.NabiRespon
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("kisahnabi")
    fun getKisahNabi() : Flowable<List<NabiRespon>>


}