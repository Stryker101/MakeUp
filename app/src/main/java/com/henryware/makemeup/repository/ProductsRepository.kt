package com.henryware.makemeup.repository

import androidx.room.withTransaction
import com.henryware.makemeup.data.MakeUpApi
import com.henryware.makemeup.database.ProductsDatabase
import com.henryware.makemeup.utils.networkBoundResource
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val api: MakeUpApi,
    private val db: ProductsDatabase
) {
    private val dao = db.getBrandsDao()

    fun getProducts() = networkBoundResource(
        query = {
            dao.getProductsFromDb()
        },
        fetch = {
            api.getProducts()
        },
        saveFetchResult = { it ->
            db.withTransaction {
                dao.addProductsToDb(it)
            }
        }
    )
}
