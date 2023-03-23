package com.henryware.makemeup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.henryware.makemeup.data.models.BrandsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductsToDb(brandsList: List<BrandsItem>)

    @Query("SELECT * FROM products WHERE brand = brand ORDER BY brand ASC")
    fun getProductsFromDb(): Flow<List<BrandsItem>>
}
