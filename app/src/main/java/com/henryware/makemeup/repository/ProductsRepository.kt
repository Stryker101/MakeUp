package com.henryware.makemeup.repository

import com.henryware.makemeup.data.local.ProductsDao
import com.henryware.makemeup.data.remote.MakeUpApi
import com.henryware.makemeup.utils.networkBoundResource
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val api: MakeUpApi,
    private val dao: ProductsDao
) {

    fun getProducts() = networkBoundResource(
        query = {
            dao.getProductsFromDb()
        },
        fetch = {
            api.getProducts()
        },
        saveFetchResult = { it ->
            dao.addProductsToDb(it)
        }
    )
}
