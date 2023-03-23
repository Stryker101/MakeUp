package com.henryware.makemeup.data.remote

import com.henryware.makemeup.data.models.BrandsItem
import retrofit2.http.GET

interface MakeUpApi {

    @GET("/api/v1/products.json")
    suspend fun getProducts(): List<BrandsItem>
}
