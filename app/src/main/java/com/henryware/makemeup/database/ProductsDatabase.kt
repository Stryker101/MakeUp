package com.henryware.makemeup.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.henryware.makemeup.response.BrandsItem
import com.henryware.makemeup.utils.Converters

@Database(entities = [BrandsItem::class], version = 1)

@TypeConverters(Converters::class)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getBrandsDao(): BrandsDAO
}
