package com.henryware.makemeup.data

import com.henryware.makemeup.response.BrandsItem
import retrofit2.http.GET

interface MakeUpApi {

    @GET("/api/v1/products.json")
    suspend fun getProducts(): List<BrandsItem>
}
